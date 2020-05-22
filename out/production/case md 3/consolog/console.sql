delimiter //
create procedure selectReviewindex()
begin
    select id_review, pointevaluate, name_review, destinations, titleposts, picture
    from postsreview
    inner join  destinationstravel on postsreview.id_destinations = destinationstravel.id_destinations
    order by dateposts desc ;
end //
delimiter ;

call selectReviewindex();