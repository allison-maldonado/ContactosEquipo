<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>

<style>
    .card{border-radius:20px}
    .form-control,.form-select{border-radius:12px;border:2px solid #D1C4E9}
    .form-control:focus,.form-select:focus{border-color:#8E24AA;box-shadow:0 0 8px rgba(142,36,170,.3)}
    .btn-purple{background:#7B1FA2;color:#fff}
    .btn-purple:hover{background:#6A1B9A;color:#fff}
    .table thead{background:#6A1B9A;color:#fff}
    .table tbody tr:hover{background:#F8F2FF}
    .badge-purple{background:#9C27B0}
</style>

<div class="container-fluid">
    <h1 class="text-center fw-bold mb-4" style="color:#6A1B9A;">Agenda de Contactos</h1>

    <div class="row g-4">

        <div class="col-lg-4">
            <div class="card shadow-lg border-0">
                <div class="card-header text-white" style="background:#6A1B9A">
                    <h4><i class="bi bi-person-vcard-fill"></i> Nuevo Contacto</h4>
                </div>
                <div class="card-body">
                    <form action="contacto" method="POST">
                        <input type="hidden" name="action" value="create">

                        <div class="mb-3">
                            <label class="form-label">Nombre</label>
                            <input type="text" class="form-control" name="nombre" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Apellidos</label>
                            <input type="text" class="form-control" name="apellidos" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Teléfono</label>
                            <input type="tel" class="form-control" name="telefono" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Teléfono alternativo</label>
                            <input type="tel" class="form-control" name="telefonoAlt">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" name="correo" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Red social</label>
                            <select class="form-select" name="redSocial" required>
                                <option disabled selected>Selecciona...</option>
                                <option>Facebook</option>
                                <option>Instagram</option>
                                <option>WhatsApp</option>
                                <option>TikTok</option>
                                <option>X</option>
                                <option>LinkedIn</option>
                                <option>Telegram</option>
                            </select>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-purple"><i class="bi bi-save"></i> Guardar contacto</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-lg-8">
            <div class="d-flex justify-content-between mb-3">
                <h4 style="color:#6A1B9A;">Lista de contactos</h4>
                <a href="contacto" class="btn btn-purple"><i class="bi bi-arrow-repeat"></i> Actualizar</a>
            </div>

            <c:choose>
                <c:when test="${empty listaContactos}">
                    <div class="alert alert-info text-center">No hay contactos registrados.</div>
                </c:when>

                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-hover align-middle shadow">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Teléfono</th>
                                <th>Telefono Alternativo</th>
                                <th>Correo</th>
                                <th>Red Social</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listaContactos}" var="contacto">
                                <tr>
                                    <td><strong>${contacto.id}</strong></td>
                                    <td>${contacto.nombre}</td>
                                    <td>${contacto.apellido}</td>
                                    <td>${contacto.telefono}</td>
                                    <td>${contacto.telefono_alternativo}</td>
                                    <td>${contacto.correo_electronico}</td>
                                    <td>${contacto.red_social}</td> </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>

    </div>
</div>

<%@ include file="layout/footer.jsp" %>
