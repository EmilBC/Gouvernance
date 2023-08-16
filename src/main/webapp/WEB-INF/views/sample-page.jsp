<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Doca</title>
        <link rel="shortcut icon" type="image/png" href="assets/images/logos/favicon.png" />
        <link rel="stylesheet" href="assets/css/styles.min.css" />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.0/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/styles.min.css" />
    </head>
    <style type="text/css">
        .bootstrap-tagsinput .tag {
            margin-right: 2px;
            color: white !important;
            background-color: #0d6efd;
            padding: 0.2rem;
            border-radius: 5px;
        }
        .clickme {
            cursor: pointer;
        }
    </style>


    <body>
        <!--  Body Wrapper -->
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
             data-sidebar-position="fixed" data-header-position="fixed">
            <!-- Sidebar Start -->
            <aside class="left-sidebar">
                <!-- Sidebar scroll-->
                <div>
                    <div class="brand-logo d-flex align-items-center justify-content-between" style='background-color: #706f6f'>
                        <a href="./index.html" class="text-nowrap logo-img">
                            <img src="assets/images/logos/logo-docaposte-blanc.png" width="180" alt="" />
                        </a>
                        <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                            <i class="ti ti-x fs-8"></i>
                        </div>
                    </div>
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                        <ul id="sidebarnav">
                            <li class="nav-small-cap">
                                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                                <span class="hide-menu">Projects</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="addProjectOnOurSystem" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-plus"></i>
                                    </span>
                                    <span class="hide-menu">Add Project To Our System</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="giveStatsToOurSystem" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-plus"></i>
                                    </span>
                                    <span class="hide-menu">Add Stats Of Your Project To Our System</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="projects" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-list"></i>
                                    </span>
                                    <span class="hide-menu">List Of Projects</span>
                                </a>
                            </li>
                            <li class="nav-small-cap">
                                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                                <span class="hide-menu">Operations</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="operations" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-article"></i>
                                    </span>
                                    <span class="hide-menu">Get Jenkins file content</span>
                                </a>
                            </li>

                        </ul>

                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!--  Sidebar End -->
            <!--  Main wrapper -->
            <div class="body-wrapper">
                <!--  Header Start -->
                <header class="app-header">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <ul class="navbar-nav">
                            <li class="nav-item d-block d-xl-none">
                                <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="javascript:void(0)">
                                    <i class="ti ti-menu-2"></i>
                                </a>
                            </li>

                        </ul>
                        <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">

                                <li class="nav-item dropdown">
                                    <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        <img src="assets/images/profile/user-1.jpg" alt="" width="35" height="35" class="rounded-circle">
                                    </a></li>
                            </ul>
                        </div>
                    </nav>
                </header>
                <!--  Header End -->
                <div class="container-fluid">
                    <div class="container-fluid">   


                        <div class="row">
                            <div class="col-lg-12 d-flex align-items-stretch">
                                <div class="card w-100">
                                    <div class="card-body p-4">
                                        <h5 class="card-title fw-semibold mb-4">Project List</h5>
                                        <div class="table-responsive">
                                            <table class="table text-nowrap mb-0 align-middle">
                                                <thead class="text-dark fs-4">
                                                    <tr>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Id</h6>
                                                        </th>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Name</h6>
                                                        </th>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Current Step</h6>
                                                        </th>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Download Init File</h6>
                                                        </th>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Init Project</h6>
                                                        </th>
                                                        <th class="border-bottom-0">
                                                            <h6 class="fw-semibold mb-0">Run Scoring</h6>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listPersons}" var="person">
                                                        <tr >
                                                            <td   class="border-bottom-0"><h6 class="fw-semibold mb-0">${person.id}</h6>
                                                                <c:if test="${person.initOnourSystem=='4' || person.initOnourSystem=='1'}">
                                                                    <span class="clickme" onclick="goTO('http://localhost:8080/SpringMVCHibernate/assets/${person.pdfPath}')" target="_blank" class="fw-normal">Click For Details</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="border-bottom-0">
                                                                <h6 class="fw-semibold mb-1">${person.projectName}</h6>
                                                                <span class="fw-normal">${person.gitStats}</span> 
                                                            </td>
                                                            <td class="border-bottom-0">
                                                                <c:if test="${person.initOnourSystem=='1'}">
                                                                    <div class="d-flex align-items-center gap-2">
                                                                        <span class="badge bg-secondary rounded-3 fw-semibold">External</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${person.initOnourSystem=='2'}">
                                                                    <div class="d-flex align-items-center gap-2">
                                                                        <span class="badge bg-primary rounded-3 fw-semibold">File Ready</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${person.initOnourSystem=='3'}">
                                                                    <div class="d-flex align-items-center gap-2">
                                                                        <span class="badge bg-danger rounded-3 fw-semibold">Project Initialized</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${person.initOnourSystem=='4'}">
                                                                    <div class="d-flex align-items-center gap-2">
                                                                        <span class="badge bg-success rounded-3 fw-semibold">Stats Generated</span>
                                                                    </div>
                                                                </c:if>



                                                            </td>
                                                            <td class="border-bottom-0">
                                                                <div class="d-flex align-items-center gap-2">
                                                                    <c:if test="${person.initOnourSystem!='1'}">
                                                                        <a href="${person.jenkinsFilePAth}" download="jenkinsfile"><i class="fa fa-download"></i></a>
                                                                        <span " class="fw-normal">Push The file<br> On Repo</span>
                                                                        </c:if>
                                                                </div>
                                                            </td>
                                                            <td class="border-bottom-0">
                                                                <div class="d-flex align-items-center gap-2">
                                                                    <c:if test="${person.initOnourSystem!='1'}">
                                                                        <a href="runSonarReportGen?projectId=${person.id}"><i class="fa fa-gear"></i></a>
                                                                        </c:if>
                                                                </div>
                                                            </td>
                                                            <td class="border-bottom-0">
                                                                <div class="d-flex align-items-center gap-2">
                                                                    <c:if test="${person.initOnourSystem!='1'}">
                                                                        <a href="runSonarReport?projectId=${person.id}"><i class="fa fa-file"></i></a>
                                                                        </c:if>
                                                                </div>
                                                            </td>
                                                        </tr> 
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/sidebarmenu.js"></script>
        <script src="assets/js/app.min.js"></script>
        <script src="assets/libs/simplebar/dist/simplebar.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.min.js"></script>
        <script type='text/javascript'>
                                                                        function goTO(val) {
                                                                            alert("hello");
                                                                            window.open(val, '_blank');
                                                                        }

        </script>
        <script>
            $(function () {
                $('input')
                        .on('change', function (event) {
                            var $element = $(event.target);
                            var $container = $element.closest('.example');
                            if (!$element.data('tagsinput'))
                                return;
                            var val = $element.val();
                            if (val === null)
                                val = 'null';
                            var items = $element.tagsinput('items');
                            $('code', $('pre.val', $container)).html(
                                    $.isArray(val)
                                    ? JSON.stringify(val)
                                    : '"' + val.replace('"', '\\"') + '"'
                                    );
                            $('code', $('pre.items', $container)).html(
                                    JSON.stringify($element.tagsinput('items'))
                                    );
                        })
                        .trigger('change');
            });
        </script>
    </body>

</html>