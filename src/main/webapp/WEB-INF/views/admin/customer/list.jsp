<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="page-header">
                <h1 style="font-weight: normal; font-family: 'Times New Roman', Times, serif;">
                    Danh sách khách hàng
                </h1>
            </div>
            <!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box" style="font-family: 'Times New Roman', Times, serif">
                        <div class="widget-header">
                            <h4 class="widget-title" style="font-weight: normal; font-family: 'Times New Roman', Times, serif;">
                                Tìm kiếm
                            </h4>
                            <span class="widget-toolbar">
                                <a href="#" data-action="reload">
                                    <i class="ace-icon fa fa-refresh"></i>
                                </a>
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form id="listForm" method="GET" action="/admin/customer-list" modelAttribute="customerSearchRequest">
                                    <input type="hidden" name="page" value="1"/>
                                    <input type="hidden" name="limit" value="2"/>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label>Tên khách hàng</label>
                                                    <form:input path="fullName" class="form-control"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label>Di động</label>
                                                    <input type="number" class="form-control" name="phone" value="${customerSearchRequest.phone}"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label>Email</label>
                                                    <input type="email" class="form-control" name="email" value="${customerSearchRequest.email}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label>Nhân viên phụ trách</label>
                                                    <form:select path="staffId" cssClass="form-control">
                                                        <form:option value="" label="---Chọn NV---"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label>Tình trạng</label>
                                                    <form:select path="status" cssClass="form-control">
                                                        <form:option value="" label="---Chọn tình trạng---"/>
                                                        <form:options items="${statusMap}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button class="btn btn-sm btn-primary" id="btnSearch">
                                                        <i class="ace-icon glyphicon glyphicon-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <div class="pull-right">
                            <a class="btn btn-primary" href="/admin/customer-edit">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </a>
                            <button title="Xóa khách hàng" class="btn btn-danger" id="btnDeleteCustomer">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-fill-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M10 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </div>
                    </sec:authorize>
                </div>
            </div>

            <div class="hr hr-18 dotted hr-double"></div>

            <div class="col-xs-12">
                <form action="<c:url value='/admin/customer-list'/>" id="formSubmit" method="get">
                    <display:table id="customerTable" name="${customerList}" class="table table-striped table-bordered table-hover" requestURI="/admin/customer-list" keepStatus="true">
                        <sec:authorize access="hasRole('ROLE_MANAGER')">
                            <display:column title="<label class='pos-rel'><input type='checkbox' class='ace' id='selectAllCheckboxBuilding' onclick='toggle(this)'/><span class='lbl'></span></label>" media="html" headerClass="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" name="customer-checkbox" value="${customerTable.id}"/>
                                    <span class="lbl"></span>
                                </label>
                            </display:column>
                        </sec:authorize>
                        <display:column property="fullName" title="Tên khách hàng"/>
                        <display:column property="phone" title="Di động"/>
                        <display:column property="email" title="Email"/>
                        <display:column property="demand" title="Nhu cầu"/>
                        <display:column property="createdBy" title="Người thêm"/>
                        <display:column property="createdDate" title="Ngày thêm"/>
                        <display:column property="status.statusName" title="Tình trạng"/>
                        <display:column title="Thao tác" media="html">
                            <div>
                                <sec:authorize access="hasRole('ROLE_MANAGER')">
                                    <button title="Giao khách" class="btn btn-sm btn-success" onclick="assignmentCustomer(${customerTable.id})" name="assignmentCustomerBtn">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5m0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5"/>
                                        </svg>
                                    </button>
                                    <button data-customer-id="${customerTable.id}" title="Xóa khách" class="btn btn-sm btn-danger btnDeleteOneCustomer">
                                        <i class="ace-icon glyphicon glyphicon-trash"></i>
                                    </button>
                                </sec:authorize>
                                <a href="/admin/customer-edit-${customerTable.id}" class="btn btn-sm btn-info" title="Sửa thông tin khách">
                                    <i class="ace-icon fa fa-pencil-square-o"></i>
                                </a>
                            </div>
                        </display:column>
                    </display:table>
                    <ul class="pagination" id="pagination"></ul>
                    <input type="hidden" value="" id="page" name="page">
                    <input type="hidden" value="" id="limit" name="limit">
                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
</div>
<!-- /.main-content -->

<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="assignmentCustomerModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <!-- a table -->
                    <table class="table table-bordered" id="staffList">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel"> Chọn </label>
                            </th>
                            <th class="center">Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--loadStaffs(buildingId)--%>
                        </tbody>
                    </table>
                    <input type="hidden" id="customerId" name="customerId" value=""/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnAssignCustomer">
                        Giao khách
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.querySelectorAll('.btnDeleteOneCustomer').forEach(function(button) {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            var id = button.getAttribute('data-customer-id');
            btnDeleteOneCustomer(id);
        });
    });

    $("#btnSearch").click(function (event) {
        event.preventDefault();
        $('#listForm').submit();
    });

    $('button[name="assignmentCustomerBtn"]').click(event => {
        event.preventDefault();
    });

    var currentPage = ${customerSearchRequest.page};
    var totalPage = ${customerSearchRequest.totalPages};
    console.log(currentPage);
    console.log(totalPage);

    $(() => {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: (event, page) => {
                if (currentPage != page) {
                    $('#page').val(page);
                    $('#limit').val(2);
                    $('#listForm input, #listForm select').each(function() {
                        var input = $(this);
                        if (input.attr('type') === 'checkbox' && !input.is(':checked')) {
                            return;
                        }
                        $('#formSubmit').append('<input type="hidden" name="' + input.attr('name') + '" value="' + input.val() + '" class="copied" />');
                    });
                    $('#formSubmit').submit();
                }
            }
        });
    });

    function toggle(source) {
        var checkboxes = document.getElementsByName('customer-checkbox');
        for (var i = 0, n = checkboxes.length; i < n; i++) {
            checkboxes[i].checked = source.checked;
        }
    }

    function assignmentCustomer(customerId) {
        $("#assignmentCustomerModal").modal();
        $("#customerId").val(customerId);
        loadStaffs(customerId);
    }

    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: "/api/customers/" + customerId + "/staffs",
            dataType: "json",
            success: response => {
                var row = '';
                $.each(response.data, (index, item) => {
                    row += '<tr>';
                    row += '<td class="center">';
                    row += '<label class="pos-rel">';
                    row += '<input type="checkbox" class="ace" id="checkbox_"' + item.staffId + '" value="' + item.staffId + '" ' + item.checked + '/>';
                    row += '<span class="lbl"></span>';
                    row += '</label>';
                    row += '</td>';
                    row += '<td class="center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            }
        });
    }

    $("#btnAssignCustomer").click(function (event) {
        event.preventDefault();
        var data = {};
        data["customerId"] = $("#customerId").val();
        data["staffIds"] = $("#staffList").find("tbody input[type=checkbox]:checked").map(function () {
            return $(this).val();
        }).get();

        $.ajax({
            type: "PUT",
            url: "/api/customers/staffs",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                alert("customer assigned");
            },
            failure: function () {
                alert("customer not assigned");
            },
            error: function () {
                alert("an error occurred");
            },
        });
    });

    $("#btnDeleteCustomer").click(function (event) {
        event.preventDefault();
        var data = {};
        data["customerIds"] = $("#customerTable").find("tbody input[type=checkbox]:checked").map(function () {
            return $(this).val();
        }).get();
        deleteCustomer(data);
    });

    function btnDeleteOneCustomer(id) {
        deleteCustomer({"customerIds": [id]});
    }

    function deleteCustomer(data) {
        $.ajax({
            type: "POST",
            url: "/api/customers" + (data["customerIds"] === undefined || data["customerIds"].length === 0 ? "" : "/" + data["customerIds"]),
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                alert("customer deleted");
                window.location.replace("/admin/customer-list");
            },
            failure: function () {
                alert("customer not deleted");
            },
            error: function (data) {
                data["customerIds"] === undefined || data["customerIds"].length === 0 ? alert("no customer to delete") : alert("an error occurred");
            },
        });
    }
</script>

</body>
</html>
