$(function() {
    // 个人中心
    $('aside dl').click(function() {
        $(this).children('dd').slideToggle(10);
        $(this).children('dt').children('i').toggleClass('hide');
    });
    // 订单部分选项卡
    $('.order_title li').mouseenter(function() {
        $(this).addClass('line');
        $(this).siblings().removeClass('line');
        var i = $(this).index();
        $('.order_content li').removeClass('show');
        $('.order_content li').eq(i).addClass('show');
    })

    // 购物车页面倒计时
    // 倒计时函数
    function GetRTime() {
        var EndTime = new Date('2018/05/10 00:00:00');
        var NowTime = new Date();
        var t = EndTime.getTime() - NowTime.getTime();
        var d = 0;
        var h = 0;
        var m = 0;
        var s = 0;
        if (t >= 0) {
            d = Math.floor(t / 1000 / 60 / 60 / 24);
            h = Math.floor(t / 1000 / 60 / 60 % 24);
            m = Math.floor(t / 1000 / 60 % 60);
            s = Math.floor(t / 1000 % 60);
        }
        if (h < 10) {
            h = '0' + h;
        }
        if (m < 10) {
            m = '0' + m;
        }
        if (s < 10) {
            s = '0' + s;
        }
        // $('.day').html(d);
        // $('.hour').html(h);
        $('.minite').html(m);
        $('.seconed').html(s);
    }
    setInterval(GetRTime, 0);

    // 购物车
    $(document).scroll(function() {
        var _top = $('.promises').offset().top - $(window).height();
        if ($(document).scrollTop() >= _top) {
            $('.accounts_time').removeClass('accounts_time_fixed');
        } else {
            $('.accounts_time').addClass('accounts_time_fixed');
        }
        // console.log(_top)
    });
    $('.cart_countdowm .iconfont').mouseover(function() {
        $('.cart_countdowm').find('.cart_text').animate({
            'top': 20,
            'opacity': 1
        }, 200)
    });
    $('.cart_countdowm').mouseleave(function() {
        $('.cart_countdowm').find('.cart_text').animate({
            'top': 30,
            'opacity': 0
        }, 0)
    })
})
