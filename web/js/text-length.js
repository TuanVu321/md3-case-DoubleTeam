$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content1').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content1').html([
                txtStart,
                // $('#link1').html(),
                // '<a href="/review?"/id=getTop6.get(0).getId_review()"/">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});

$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content2').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content2').html([
                txtStart,
                // $('#link2').html(),
                // '<a href="#">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});

$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content3').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content3').html([
                txtStart,
                // $('#link3').html(),
                // '<a href="#">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});

$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content4').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content4').html([
                txtStart,
                // $('#link4').html(),
                // '<a href="#">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});

$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content5').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content5').html([
                txtStart,
                // $('#link5').html(),
                // '<a href="#">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});

$(function () {
    let limitW = 100;
    let char = 6;
    let txt = $('#content6').html();
    let txtStart = txt.slice(0, limitW).replace(/\w+$/, '');
    let txtEnd = txt.slice(txtStart.length);
    if (txtEnd.replace(/\s+$/, '').split(' ').length > char) {
        $('#content6').html([
                txtStart,
                // $('#link6').html(),
                // '<a href="#">...xem thêm</a>',
                '<span class="detail">',
                txtEnd,
                '</span>'
            ].join('')
        );
    }
    $('span.detail').hide();
});
