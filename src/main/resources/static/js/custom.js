$(document).ready(function () {
    $(".btnAddToCart").click(function () {
        // truyền duy nhất idChiTietSanPham thôi
        //bên ApiController truy vấn lấy ChiTietSanPham ra xử lý
        var idChiTiet = $(this).attr("data-idChiTiet");

        $.ajax({
            url: "/api/ThemGioHang",
            type: "GET",
            data: {
                idChiTiet: idChiTiet
            },
            // value đại diện cho giá trị return về
            success: function (value) {
                $("#soluong-giohang").text(value + "")
            }
        })
    })

    $(".btnXoaSpTrongGio").click(function () {
        var THIS = $(this);
        var idChiTiet = THIS.attr("data-idChiTiet-Gio")

        $.ajax({
            url: "/api/XoaGioHang",
            type: "GET",
            data: {
                idChiTiet: idChiTiet
            },
            success: function (value) {
                if (value != "notok") {
                    // remove thẻ <tr> và cập nhật lại số lượng trong giỏ
                    THIS.closest("tr").remove()
                    $("#soluong-giohang").text(value + "")

                    if (value === "0") {
                        $("#btnUpdateCart").remove()
                    }
                }
            }
        })
    })

    /*----------------------------------------- dashboard -----------------------------------------*/

    $(".myPagination").click(function () {
        var index = $(this).text()
        var idDanhMuc = $(this).attr("data-idDanhMuc")

        $.ajax({
            url: "/api/pagination",
            type: "POST",
            data: {
                index: index,
                idDanhMuc: idDanhMuc
            },
            success: function (value) {
                var tbodysanpham = $("#dataTable").find("tbody");
                tbodysanpham.empty();
                tbodysanpham.append(value);
            }
        })
    })

    $("body").on("click", ".btnUpdateProduct", function () {
        var idSanPham = $(this).attr("data-id")

        // đổi tile thành sửa sản phẩm
        $("#txtThemVaSua").text("Cập nhật sản phẩm")
        // ẩn btnThem và hiển thị btnCapNhat, btnThoat
        $("#btnThemSanPham").addClass("hidden")
        $("#btnCapNhatSanPham").removeClass("hidden")
        $("#btnThoatCapNhat").removeClass("hidden")

        $.ajax({
            url: "/api/DoRaForm",
            type: "POST",
            data: {
                idSanPham: idSanPham
            },
            success: function (value) {
                console.log(value)
                $("#masp").val(value.idSanPham)
                $("#tensp").val(value.tenSanPham)
                $("#danhmucsp").val(value.danhMucSanPham.idDanhMuc)
                $("#giasp").val(value.giaTien)
                $("#mota").val(value.moTa)
                if (value.gioiTinh === "male") {
                    $("#rdbMale").prop("checked", true)
                } else if (value.gioiTinh === "female") {
                    $("#rdbFemale").prop("checked", true)
                } else {
                    $("#rdbNoSex").prop("checked", true)
                }
            }
        })
    })

    $("body").on("click", ".btnDetail", function () {
        var idSanPham = $(this).attr("data-id")
        $("#idSanPham").val(idSanPham)

        $.ajax({
            url: "/api/DoRaTableDetaild",
            type: "POST",
            data: {
                idSanPham: idSanPham
            },
            success: function (value) {
                var tbodysanpham = $("#tableChiTietSP").find("tbody");
                tbodysanpham.empty();
                tbodysanpham.append(value);
            }
        })
    })

    $("body").on("click", ".btnXoaSanPham", function () {
        var THIS = $(this)
        var idSanPham = $(this).attr("data-id")
        alert("xóa sản phẩm này nha: " + idSanPham)

        $.ajax({
            url: "/api/XoaSanPham",
            type: "POST",
            data: {
                idSanPham: idSanPham
            },
            success: function (value) {
                THIS.closest("tr").remove()
            }
        })
    })

    $(".btnUpdateCategory").click(function () {
        var idDanhMuc = $(this).attr("data-idDanhMuc")

        // đổi tile thành sửa sản phẩm
        $("#txtThemAndSua").text("Cập nhật danh mục")
        // ẩn btnThem và hiển thị btnCapNhat, btnThoat
        $("#btnThemDanhMuc").addClass("hidden")
        $("#btnSuaDanhMuc").removeClass("hidden")
        $("#btnCancel").removeClass("hidden")

        $.ajax({
            url: "/api/DoRaFormDanhMuc",
            type: "POST",
            data: {
                idDanhMuc: idDanhMuc
            },
            success: function (value) {
                console.log(value)
                $("#madm").val(value.idDanhMuc)
                $("#tendm").val(value.tenDanhMuc)
                $("#metatitle").val(value.metaTitle)
                if (value.status === true) {
                    $("#rdbEnable").prop("checked", true)
                } else {
                    $("#rdbDisable").prop("checked", true)
                }
            }
        })
    })
})


/*************************************************** ko dc đụng vô ***************************************************/
/**
 * Template Name: Daily Shop
 * Version: 1.0
 * Template Scripts
 * Author: MarkUps
 * Author URI: http://www.markups.io/

 Custom JS

 1. CARTBOX
 2. TOOLTIP
 3. PRODUCT VIEW SLIDER
 4. POPULAR PRODUCT SLIDER (SLICK SLIDER)
 5. FEATURED PRODUCT SLIDER (SLICK SLIDER)
 6. LATEST PRODUCT SLIDER (SLICK SLIDER)
 7. TESTIMONIAL SLIDER (SLICK SLIDER)
 8. CLIENT BRAND SLIDER (SLICK SLIDER)
 9. PRICE SLIDER  (noUiSlider SLIDER)
 10. SCROLL TOP BUTTON
 11. PRELOADER
 12. GRID AND LIST LAYOUT CHANGER
 13. RELATED ITEM SLIDER (SLICK SLIDER)
 **/

jQuery(function ($) {


    /* ----------------------------------------------------------- */
    /*  1. CARTBOX 
    /* ----------------------------------------------------------- */

    jQuery(".aa-cartbox").hover(function () {
            jQuery(this).find(".aa-cartbox-summary").fadeIn(500);
        }
        , function () {
            jQuery(this).find(".aa-cartbox-summary").fadeOut(500);
        }
    );

    /* ----------------------------------------------------------- */
    /*  2. TOOLTIP
    /* ----------------------------------------------------------- */
    jQuery('[data-toggle="tooltip"]').tooltip();
    jQuery('[data-toggle2="tooltip"]').tooltip();

    /* ----------------------------------------------------------- */
    /*  3. PRODUCT VIEW SLIDER 
    /* ----------------------------------------------------------- */

    jQuery('#demo-1 .simpleLens-thumbnails-container img').simpleGallery({
        loading_image: 'demo/images/loading.gif'
    });

    jQuery('#demo-1 .simpleLens-big-image').simpleLens({
        loading_image: 'demo/images/loading.gif'
    });

    /* ----------------------------------------------------------- */
    /*  4. POPULAR PRODUCT SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */

    jQuery('.aa-popular-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });


    /* ----------------------------------------------------------- */
    /*  5. FEATURED PRODUCT SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */

    jQuery('.aa-featured-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });

    /* ----------------------------------------------------------- */
    /*  6. LATEST PRODUCT SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */
    jQuery('.aa-latest-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });

    /* ----------------------------------------------------------- */
    /*  7. TESTIMONIAL SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */

    jQuery('.aa-testimonial-slider').slick({
        dots: true,
        infinite: true,
        arrows: false,
        speed: 300,
        slidesToShow: 1,
        adaptiveHeight: true
    });

    /* ----------------------------------------------------------- */
    /*  8. CLIENT BRAND SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */

    jQuery('.aa-client-brand-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        autoplay: true,
        autoplaySpeed: 2000,
        slidesToShow: 5,
        slidesToScroll: 1,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });

    /* ----------------------------------------------------------- */
    /*  9. PRICE SLIDER  (noUiSlider SLIDER)
    /* ----------------------------------------------------------- */

    jQuery(function () {
        if ($('body').is('.productPage')) {
            var skipSlider = document.getElementById('skipstep');
            noUiSlider.create(skipSlider, {
                range: {
                    'min': 0,
                    '10%': 10,
                    '20%': 20,
                    '30%': 30,
                    '40%': 40,
                    '50%': 50,
                    '60%': 60,
                    '70%': 70,
                    '80%': 80,
                    '90%': 90,
                    'max': 100
                },
                snap: true,
                connect: true,
                start: [20, 70]
            });
            // for value print
            var skipValues = [
                document.getElementById('skip-value-lower'),
                document.getElementById('skip-value-upper')
            ];

            skipSlider.noUiSlider.on('update', function (values, handle) {
                skipValues[handle].innerHTML = values[handle];
            });
        }
    });


    /* ----------------------------------------------------------- */
    /*  10. SCROLL TOP BUTTON
    /* ----------------------------------------------------------- */

    //Check to see if the window is top if not then display button

    jQuery(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.scrollToTop').fadeIn();
        } else {
            $('.scrollToTop').fadeOut();
        }
    });

    //Click event to scroll to top

    jQuery('.scrollToTop').click(function () {
        $('html, body').animate({scrollTop: 0}, 800);
        return false;
    });

    /* ----------------------------------------------------------- */
    /*  11. PRELOADER
    /* ----------------------------------------------------------- */

    jQuery(window).load(function () { // makes sure the whole site is loaded      
        jQuery('#wpf-loader-two').delay(200).fadeOut('slow'); // will fade out      
    })

    /* ----------------------------------------------------------- */
    /*  12. GRID AND LIST LAYOUT CHANGER 
    /* ----------------------------------------------------------- */

    jQuery("#list-catg").click(function (e) {
        e.preventDefault(e);
        jQuery(".aa-product-catg").addClass("list");
    });
    jQuery("#grid-catg").click(function (e) {
        e.preventDefault(e);
        jQuery(".aa-product-catg").removeClass("list");
    });


    /* ----------------------------------------------------------- */
    /*  13. RELATED ITEM SLIDER (SLICK SLIDER)
    /* ----------------------------------------------------------- */

    jQuery('.aa-related-item-slider').slick({
        dots: false,
        infinite: false,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 4,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
            // You can unslick at a given breakpoint now by adding:
            // settings: "unslick"
            // instead of a settings object
        ]
    });

});

