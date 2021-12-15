<!<!-- Usando la librería core de JSTL -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Control de clientes</title>
    </head>
    <body>
        <!<!-- Cabecero usando bootstrap -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

        <!<!-- Botones de navegación -->
        <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacion.jsp" />

        <!-- Listado Clientes -->
        <jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp" />

        <!<!-- Pie de página o footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/0370956726.js" crossorigin="anonymous"></script>
    </body>
</html>
