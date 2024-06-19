<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${empty customerEdit.id}">
        <title>
            Thêm khách hàng
        </title>
    </c:if>

    <c:if test="${not empty customerEdit.id}">
        <title>
            Sửa khách hàng
        </title>
    </c:if>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="page-header">
                <h1 style="font-weight: normal;font-family: 'Times New Roman', Times, serif;">
                    Quản lý khách hàng
                </h1>
            </div>

            <div class="row" style="font-family: 'Times New Roman', Times, serif">
                <form:form
                        modelAttribute="customerEdit"
                        method="get"
                        action="/admin/cusomer-edit"
                        name="formEdit"
                        class="form-horizontal"
                        id="form-edit">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label class="col-xs-3">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="fullName" class="form-control" required="required"
                                            placeholder="bắt buộc"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input path="phone" class="form-control" required="required"
                                            placeholder="bắt buộc"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Email</label>
                            <div class="col-xs-9">
                                <form:input path="email" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tên công ty</label>
                            <div class="col-xs-9">
                                <form:input path="companyName" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input path="demand" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tình trạng</label>
                            <div class="col-xs-2">
                                <form:select path="status">
                                    <form:options items="${statusMap}" required="required" id="district"
                                                  class="form-control"/>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3"></label>

                            <div class="col-xs-9">
                                <c:if test="${empty customerEdit.id}">
                                    <button
                                            class="btn btn-primary"
                                            id="btnAddCustomer">
                                        Thêm khách hàng
                                    </button>
                                </c:if>

                                <c:if test="${not empty customerEdit.id}">
                                    <c:if test="${currentUserRoles.contains('ROLE_MANAGER')}">
                                        <button
                                                class="btn btn-primary"
                                                id="btnUpdateCustomer"
                                                onclick="editCustomer(${customerEdit.id})">
                                            Sửa thông tin khách hàng
                                        </button>
                                    </c:if>
                                </c:if>

                                <a class="btn btn-primary" href="./customer-list">
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

    $("#btnAddCustomer").click(event => {
        event.preventDefault();

        if (!validateField("#fullName", "Tên khách là bắt buộc.")) {
            return;
        }
        if (!validateField("#phone", "Di động là bắt buộc.")) {
            return;
        }

        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            data["" + it.name + ""] = it.value;
        });
        insertCustomer(data);
    })

    $('#btnUpdateCustomer').click(event => {
        event.preventDefault();
    })
    function editCustomer(customerId) {
        if (!validateField("#fullName", "Tên khách là bắt buộc.")) {
            return;
        }
        if (!validateField("#phone", "Di động là bắt buộc.")) {
            return;
        }
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            data["" + it.name + ""] = it.value;
        });
        data["id"] = customerId;
        updateCustomer(data);
    }

    function insertCustomer(data) {
        $.ajax({
            type: "PUT",
            url: "/api/customers",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (respond) => {
                alert(respond);
                window.location.replace("/admin/customer-list");
            },
            failure: () => {
                alert("customer not added");
            },
            error: () => {
                alert("an error occurred");
            },
        });
    }

    function updateCustomer(data) {
        $.ajax({
            type: "POST",
            url: "/api/customers",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (respond) => {
                alert(respond);
                window.location.replace("/admin/customer-list");
            },
            failure: () => {
                alert("customer not edited");
            },
            error: () => {
                alert("an error occurred");
            },
        });
    }
</script>
</body>
</html>
