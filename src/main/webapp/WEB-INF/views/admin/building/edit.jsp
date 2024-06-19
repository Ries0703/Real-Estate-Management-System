<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<html>
<head>
    <c:if test="${empty buildingEdit.id}">

        <title>
            Thêm tòa nhà
        </title>
    </c:if>

    <c:if test="${not empty buildingEdit.id}">

        <title>
            Sửa tòa nhà
        </title>
    </c:if>

</head>
<body>
<div class="main-content">
    <div class="main-content-inner">

        <div class="page-content">
            <div class="page-header">
                <h1
                        style="
                  font-weight: normal;
                  font-family: 'Times New Roman', Times, serif;
                "
                >
                    Quản lý tòa nhà
                </h1>
            </div>

            <div
                    class="row"
                    style="font-family: 'Times New Roman', Times, serif"
            >
                <form:form
                        modelAttribute="buildingEdit"
                        method="get"
                        action="${buildingEditURL}"
                        name="formEdit"
                        class="form-horizontal"
                        id="form-edit"
                >
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label class="col-xs-3">Tên tòa nhà</label>
                            <div class="col-xs-9">
                                <form:input path="name" class="form-control" required="required"
                                            placeholder="bắt buộc"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-2">
                                <form:select path="district">
                                    <form:option value="" label="---Chọn quận---"/>
                                    <form:options items="${districtCodes}" required="required" id="district"
                                                  class="form-control"
                                                  placeholder="bắt buộc"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">
                                <form:input path="ward" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">
                                <form:input path="street" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input path="structure" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="numberOfBasement"
                                        value="${buildingEdit.numberOfBasement}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="floorArea"
                                        value="${buildingEdit.floorArea}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
                                <form:input path="direction" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="level"
                                        value="${buildingEdit.level}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <input
                                        type="text"
                                        class="form-control"
                                        name="rentArea"
                                        value="${buildingEdit.rentArea}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <input
                                        id="rentPrice"
                                        type="number"
                                        class="form-control"
                                        name="rentPrice"
                                        value="${buildingEdit.rentPrice}"
                                        placeholder="bắt buộc"
                                        required
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <div class="col-xs-9">
                                <form:input path="rentPriceDescription" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="serviceFee"
                                        value="${buildingEdit.serviceFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="carFee"
                                        value="${buildingEdit.carFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí mô tô</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="motoFee"
                                        value="${buildingEdit.motoFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="overtimeFee"
                                        value="${buildingEdit.overtimeFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="electricityFee"
                                        value="${buildingEdit.electricityFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="deposit"
                                        value="${buildingEdit.deposit}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="payment"
                                        value="${buildingEdit.payment}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input path="decorationTime" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
                                <input
                                        type="number"
                                        class="form-control"
                                        name="brokerageFee"
                                        value="${buildingEdit.brokerageFee}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Loại tòa nhà (Phải chọn 1 loại)</label>
                            <div class="col-xs-9">
                                <form:checkboxes path="typeCode" id="typeCode" items="${typeCodes}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input path="note" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right"  type="file" id="uploadImage" name="image" value="${buildingEdit.image}"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.image}">
                                    <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" alt="image"
                                         style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty buildingEdit.image}">
                                    <img alt="BuildingImage" src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3"></label>

                            <div class="col-xs-9">
                                <c:if test="${empty buildingEdit.id}">
                                    <button
                                            class="btn btn-primary"
                                            id="btnAddBuilding"
                                    >
                                        Thêm tòa nhà
                                    </button>
                                </c:if>

                                <c:if test="${not empty buildingEdit.id}">
                                    <c:if test="${currentUserRoles.contains('ROLE_MANAGER')}">
                                        <button
                                                class="btn btn-primary"
                                                id="btnUpdateBuilding"
                                                onclick="editBuilding(${buildingEdit.id})"
                                        >
                                            Sửa tòa nhà
                                        </button>
                                    </c:if>
                                </c:if>


                                <a class="btn btn-primary" href="./building-list">
                                    Hủy thao tác
                                </a>

                            </div>
                        </div>

                    </div>
                </form:form>

            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
</div>
<!-- /.main-content -->

<script>
    var imageBase64 = '';
    var imageName = '';

    function validateField(selector, errorMessage, isCheckbox = false) {
        if (isCheckbox) {
            // Check if at least one checkbox is checked
            if (!$(selector).is(':checked')) {
                alert(errorMessage);
                return false;
            }
        } else if (!$(selector).val().trim()) {
            // Check if the field is filled and not only spaces
            alert(errorMessage);
            return false;
        }
        return true;
    }

    $("#btnAddBuilding").click(event => {
        event.preventDefault();

        if (!validateField("#name", "Tên tòa nhà là bắt buộc.")) {
            return;
        }
        if (!validateField("#rentPrice", "Giá thuê là bắt buộc.")) {
            return;
        }
        if (!validateField("#district", "Quận là bắt buộc.")) {
            return;
        }
        if (!validateField("input[name='typeCode']", "Chọn ít nhất 1 loại tòa nhà.", true)) {
            return;
        }
        var data = {};
        var typeCodes = [];
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            if (it.name === "typeCode") {
                typeCodes.push(it.value);
            } else {
                data["" + it.name + ""] = it.value;
            }
        });
        if ('' !== imageBase64) {
            data['imageBase64'] = imageBase64;
            data['imageName'] = imageName;
        }
        data["typeCode"] = typeCodes;
        insertBuilding(data);
    })

    $('#btnUpdateBuilding').click(event => {
        event.preventDefault();
    })
    function editBuilding(buildingId) {
        if (!validateField("#name", "Tên tòa nhà là bắt buộc.")) {
            return;
        }
        if (!validateField("#rentPrice", "Giá thuê là bắt buộc.")) {
            return;
        }
        if (!validateField("#district", "Quận là bắt buộc.")) {
            return;
        }
        if (!validateField("input[name='typeCode']", "Chọn ít nhất 1 loại tòa nhà.", true)) {
            return;
        }

        var data = {};
        var typeCodes = [];
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            if (it.name === "typeCode") {
                typeCodes.push(it.value);
            } else {
                data["" + it.name + ""] = it.value;
            }
        });
        if ('' !== imageBase64 && '' !== imageName) {
            data['imageBase64'] = imageBase64;
            data['imageName'] = imageName;
        }
        data["typeCode"] = typeCodes;
        data["id"] = buildingId;
        updateBuilding(data);
    }


    function insertBuilding(data) {
        $.ajax({
            type: "PUT",
            url: "/api/buildings",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (respond) => {
                alert(respond);
                window.location.replace("/admin/building-list");
            },
            failure: () => {
                alert("building not added");
            },
            error: () => {
                alert("an error occurred");
            },
        });
    }

    function updateBuilding(data) {
        $.ajax({
            type: "POST",
            url: "/api/buildings",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (respond) => {
                alert(respond);
                window.location.replace("/admin/building-list");
            },
            failure: () => {
                alert("building not edited");
            },
            error: () => {
                alert("an error occurred");
            },
        });
    }


    $('#uploadImage').change(function (event) {
        event.preventDefault();
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>