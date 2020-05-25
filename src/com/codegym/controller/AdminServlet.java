package com.codegym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codegym.model.Review;
import com.codegym.model.SignupAccount;
import com.codegym.service.DatabaseServiceImpl;
import com.codegym.service.ReviewServletImpl;

@WebServlet(name="AdminServlet", urlPatterns="/admin_dashboard")
public class AdminServlet extends HttpServlet {
    private final DatabaseServiceImpl databaseService = new DatabaseServiceImpl();
    private final ReviewServletImpl reviewServlet = new ReviewServletImpl();
    private static final String SELECT_ALL_ACCOUNTS = "select * from c0220h1dbt.account;";
    private static final String CREATE_NEW_ACCOUNT = "insert into c0220h1dbt.account(" +
                                                                            "id_role, username, password, fullname, phone, email, address, active, online) " +
                                                                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SET_ACTIVE = "update c0220h1dbt.account set active = 1 where username = ?;";
    private static final String SET_BLOCK = "update c0220h1dbt.account set active = 0 where username = ?;";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "search":
                searchByName(request, response);
                break;
            case "actived":
                activeAccount(request, response);
                break;
            case "blocked":
                blockAccount(request, response);
                break;
            case "showAccountsList":
                showDashboard(request, response);
                break;
            case "create":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteAccount(request, response);
                break;
            case "deleteReview":
                deleteReview(request, response);
                break;
            case "showReviewList":
                showReview(request, response);
                break;
            case "showNewReviewForm":
                showNewReviewForm(request, response);
            case "editPostReview":
                showEditPostReview(request, response);
                break;
            case "404Error":
                show404ErrorPage(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "searchReview":
                searchReview(request, response);
                break;
            case "deleteReview":
                deleteReview(request, response);
                break;
            case "search":
                searchByName(request, response);
                break;
            case "actived":
                activeAccount(request, response);
                break;
            case "blocked":
                blockAccount(request, response);
                break;
            case "create":
                createNewAccount(request, response);
                break;
            case "showNewReviewForm":
                createNewPostReview(request, response);
                break;
            case "edit":
                editAccount(request, response);
                break;
            case "editPostReview":
                editPostreview(request, response);
                break;
            default:
                break;
        }
    }

    private void searchReview(HttpServletRequest request, HttpServletResponse response) {
        String search_query = "select * from c0220h1dbt.postsreview where titleposts like ? or name_review = ?;";
        Connection conn = databaseService.setCheckForeignKey();
        List<Review> reviewList = new ArrayList<>();
        int id_review = 0;
        String name = "";
        String title = "";
        String content = "";
        Date datePost = null;
        int star = 0;
        String picture = "";
        try {
            String namePlace = request.getParameter("searchName");
            PreparedStatement pstmt = conn.prepareStatement(search_query);
            pstmt.setString(1, "%" + namePlace + "%");
            pstmt.setString(2, "%" + namePlace + "%");

            ResultSet rs = pstmt.executeQuery();
            Review review = null;
            while (rs.next()) {
                id_review = rs.getInt("id_review");
                name = rs.getString("name_review");
                title = rs.getString("titleposts");
                content = rs.getString("content");
                datePost = rs.getDate("dateposts");
                star = rs.getInt("pointevaluate");
                picture = rs.getString("picture");

                review = new Review(id_review, name, title, content, datePost, star, picture);
                reviewList.add(review);
            }

            request.setAttribute("reviewList", reviewList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/serach-postreview.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response) {
        int id_review = Integer.parseInt(request.getParameter("id_review"));
        String delete_query = "delete from c0220h1dbt.postsreview where id_review = ?;";
        Connection conn = databaseService.setCheckForeignKey();

        try {
            PreparedStatement pstmt = conn.prepareStatement(delete_query);
            pstmt.setInt(1, id_review);
            pstmt.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_dashboard?action=showReviewList");
            try {
                dispatcher.forward(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String search_query = "select * from c0220h1dbt.account where username = ?;";
        Connection conn = databaseService.setCheckForeignKey();
        try {
            String username = request.getParameter("usernameSearch");
            PreparedStatement pstmt = conn.prepareStatement(search_query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            List<SignupAccount> listAccount = getAccountList(rs);

            request.setAttribute("listAccount", listAccount);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/result-search.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void activeAccount(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = databaseService.setCheckForeignKey();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SET_ACTIVE);
            String usernameAcc = request.getParameter("usernameAcc");
            pstmt.setString(1, usernameAcc);
            pstmt.executeUpdate();

            showDashboard(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void blockAccount(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = databaseService.setCheckForeignKey();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SET_BLOCK);
            String usernameAcc = request.getParameter("usernameAcc");
            pstmt.setString(1, usernameAcc);
            pstmt.executeUpdate();

            showDashboard(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void editPostreview(HttpServletRequest request, HttpServletResponse response) {
        int destination = Integer.parseInt(request.getParameter("destination"));
        String nameReview = request.getParameter("nameReview");
        String title = request.getParameter("title");
        String picture = request.getParameter("picture");
        String content = request.getParameter("content");
        int point = Integer.parseInt(request.getParameter("point"));

        String query_update = "update c0220h1dbt.postsreview set id_destinations = ?, name_review = ?, titleposts = ?, picture = ?, content = ?, pointevaluate = ? " +
                                        " where id_destinations = ?";
        try {
            Connection conn = databaseService.setCheckForeignKey();
            PreparedStatement pstmt = conn.prepareStatement(query_update);
            pstmt.setInt(1, destination);
            pstmt.setString(2, nameReview);
            pstmt.setString(3, title);
            pstmt.setString(4, picture);
            pstmt.setString(5, content);
            pstmt.setInt(6, point);
            pstmt.setInt(7, destination);

            pstmt.executeUpdate();

            RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit-postreview.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showEditPostReview(HttpServletRequest request, HttpServletResponse response) {
        int id_review = Integer.parseInt(request.getParameter("id_review"));
        String sql_query = "select id_destinations, id_account, name_review, titleposts, picture, content, pointevaluate from c0220h1dbt.postsreview " +
                                  "where id_review = ?;";
        try {
            Connection conn = databaseService.setCheckForeignKey();
            PreparedStatement pstmt = conn.prepareStatement(sql_query);
            pstmt.setInt(1, id_review);
            ResultSet rs = pstmt.executeQuery();
            java.util.Date now = new java.util.Date();
            java.util.Date utilDate = new java.util.Date(now.getTime());
            Review oldReview = null;

            while (rs.next()) {
                int id_destination = rs.getInt("id_destinations");
                int id_account = rs.getInt("id_account");
                String name_review = rs.getString("name_review");
                String titleposts = rs.getString("titleposts");
                String picture = rs.getString("picture");
                String content = rs.getString("content");
                int pointevaluate = rs.getInt("pointevaluate");

                oldReview = new Review(id_destination, id_account, name_review, titleposts, picture, content, pointevaluate);
            }

            request.setAttribute("oldReview", oldReview);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit-postreview.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void show404ErrorPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("404Error.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createNewPostReview(HttpServletRequest request, HttpServletResponse response) {
        int id_destination = Integer.parseInt(request.getParameter("destination"));
        String name_review = request.getParameter("nameReview");
        String title = request.getParameter("title");
        String picture = request.getParameter("picture");
        String content = request.getParameter("content");
        int point_value = Integer.parseInt(request.getParameter("point"));
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        String formatterDate = currentDate.format(dateTimeFormatter);
        try {
        java.util.Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(formatterDate);
        reviewServlet.createNewReview(id_destination, 1, name_review, title, content, picture, point_value, dateObj);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create-new-postreview.jsp");
        dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void showReview(HttpServletRequest request, HttpServletResponse response) {
        String spageid = request.getParameter("pageNo");
        int pageid = Integer.parseInt(spageid);
        int total = 10;

        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        List<Review> reviewList = reviewServlet.createReviewListPagnigation(pageid, total);
        request.setAttribute("reviewList", reviewList);
        request.setAttribute("spageid", spageid);
        request.setAttribute("pageid", pageid);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/post-review-admin.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        String DELETE_ACCOUNT_SQL = "delete from datareview.account where username = ?";
        String username = request.getParameter("usernameAcc");
        Connection conn = databaseService.createConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ACCOUNT_SQL);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            showDashboard(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void editAccount(HttpServletRequest request, HttpServletResponse response) {
        String usernameAcc = request.getParameter("usernameAcc");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String UPDATE_ACCOUNT = "update datareview.account set  username = ?, password = ?, fullname = ?, phone = ?, email = ?, address = ? where username = ?;";

        Connection conn = databaseService.createConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ACCOUNT);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullname);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setString(7, usernameAcc);

            pstmt.executeUpdate();

            showDashboard(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String usernameAcc = request.getParameter("usernameAcc");
        String sql_getAccount = "select id_role, username, password, fullname, phone, email, address from datareview.account where username = ?;";
        Connection conn = databaseService.createConnection();

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit-account.jsp");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql_getAccount);
            pstmt.setString(1, usernameAcc);
            ResultSet rs = pstmt.executeQuery();
            SignupAccount account = null;
            while (rs.next()) {
                int id_role = rs.getInt("id_role");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                account = new SignupAccount(id_role, username, password, fullname, phone, email, address);
            }
            request.setAttribute("account", account);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) {
        String role = request.getParameter("role");
        request.setAttribute("role", role);

        try {
        Connection conn = databaseService.createConnection();
        PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ACCOUNTS);
        ResultSet rs = pstmt.executeQuery();
        List<SignupAccount> accountList = getAccountList(rs);
        request.setAttribute("accountList", accountList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/dashboard-admin.jsp");

        dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<SignupAccount> getAccountList(ResultSet rs) {
        SignupAccount account = null;
        List<SignupAccount> accountList = new ArrayList<>();
        int id_role = 0;
        String username = "";
        String password = "";
        String fullname = "";
        String phone = "";
        String email = "";
        String address = "";
        int active = 0;
        try {
            while (rs.next()) {
                id_role = rs.getInt("id_role");
                username = rs.getString("username");
                password = rs.getString("password");
                fullname = rs.getString("fullname");
                phone = rs.getString("phone");
                email = rs.getString("email");
                address = rs.getString("address");
                active = rs.getInt("active");
                accountList.add(new SignupAccount(id_role, username, password, fullname, phone, email, address, active));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return accountList;
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create-new-account.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private SignupAccount createNewAccount(HttpServletRequest request, HttpServletResponse response) {
        //int id_role = Integer.parseInt(request.getParameter("role"));
        int id_role = 0;
        if (request.getParameter("role").equals("admin")) {
            id_role = 2;
        }
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        SignupAccount newAccount = new SignupAccount(id_role, username, password, fullname, email, address, phone);

        try {
            Connection conn = databaseService.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(CREATE_NEW_ACCOUNT);
            pstmt.setInt(1, 2);
            pstmt.setString(2,username);
            pstmt.setString(3,password);
            pstmt.setString(4,fullname);
            pstmt.setString(5,phone);
            pstmt.setString(6,email);
            pstmt.setString(7,address);
            pstmt.setInt(8,0);
            pstmt.setInt(9,0);
            pstmt.executeUpdate();

            setActiveAccount(username, conn);

            RequestDispatcher dispatcher = request.getRequestDispatcher("view/create-new-account.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }

        return newAccount;
    }

    private void setActiveAccount(String username, Connection conn) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SET_ACTIVE);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showNewReviewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create-new-postreview.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
