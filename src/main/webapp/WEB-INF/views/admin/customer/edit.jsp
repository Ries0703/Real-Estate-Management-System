<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <c:forEach var="item" items="${transactionMap}">
            <div class="col-xs-12">
                <div class="col-sm-12">
                    <h3 class="header smaller lighter blue">${item.value}</h3>
                    <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}', ${customerEdit.id})">
                        <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                    </button>
                </div>
                <c:if test="${item.key=='CSKH'}">
                    <div class="col-xs-12">
                        <table id="simple-table-cskh" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Created By</th>
                                <th>Modified Date</th>
                                <th>Modified By</th>
                                <th>Note</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="transaction" items="${transactionCSKH}">
                                <tr>
                                    <td>${transaction.createdDate}</td>
                                    <td>${transaction.createdBy}</td>
                                    <td>${transaction.modifiedDate}</td>
                                    <td>${transaction.modifiedBy}</td>
                                    <td>${transaction.note}</td>
                                    <td>
                                        <div>
                                            <button title="Sửa thông tin giao dịch" class="btn btn-sm btn-info" onclick="updateTransaction(${transaction.id}, '${fn:escapeXml(transaction.note)}', '${transaction.code}')" name="updateTransactionBtn">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
                                                </svg>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </c:if>
                <c:if test="${item.key=='DDX'}">
                <div class="col-xs-12">
                    <table id="simple-table-ddx" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Created By</th>
                            <th>Modified Date</th>
                            <th>Modified By</th>
                            <th>Note</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="transaction" items="${transactionDDX}">
                            <tr>
                                <td>${transaction.createdDate}</td>
                                <td>${transaction.createdBy}</td>
                                <td>${transaction.modifiedDate}</td>
                                <td>${transaction.modifiedBy}</td>
                                <td>${transaction.note}</td>
                                <td>
                                    <div>
                                        <button title="Sửa thông tin giao dịch" class="btn btn-sm btn-info" onclick="updateTransaction(${transaction.id}, '${fn:escapeXml(transaction.note)}')" name="updateTransactionBtn">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                                                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
                                            </svg>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<!-- /.main-content -->
<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="width-100">
                        </span>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" id="customerId" value="">
            <input type="hidden" name="code" id="code" value="">
            <input type="hidden" name="id" id="id" value="">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
        </div>
    </div>
</div>

<script>
    function transactionType(code, customerId) {
        $('#transactionTypeModal').modal()
        $('#customerId').val(customerId)
        $('#code').val(code)
    }

    function updateTransaction(id, note, code) {
        $('#id').val(id)
        $('#transactionTypeModal').modal()
        $('#transactionDetail').val(note);
        $('#code').val(code)
    }

    $(document).ready(function() {
        $('#btnAddOrUpdateTransaction').click(function(e) {
            e.preventDefault();
            var data = {};
            data['id'] = $('#id').val();
            data['customerId'] = $('#customerId').val();
            data['code'] = $('#code').val();
            data['note'] = $('#transactionDetail').val();

            if (!data['id']) {
                addTransaction(data);
            } else {
                modifyTransaction(data);
            }
        });

        function modifyTransaction(data) {
            $.ajax({
                type: "POST",
                url: "/api/customers/transactions",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function() {
                    console.log("success");
                    alert("Transaction updated successfully");
                    $('#transactionTypeModal').modal('hide'); // Hide the modal
                    window.location.reload(); // Refresh the page
                },
                error: function() {
                    console.log("failed");
                }
            });
        }

        function addTransaction(data) {
            $.ajax({
                type: "PUT",
                url: "/api/customers/transactions",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function() {
                    console.log("success");
                    alert("Transaction added successfully");
                    $('#transactionTypeModal').modal('hide'); // Hide the modal
                    window.location.reload(); // Refresh the page
                },
                error: function() {
                    console.log("failed");
                }
            });
        }
    });



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
