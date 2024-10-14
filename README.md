<h2>Handy Orden Sync Application</h2>
<br>

<p>Aplicación que realiza polling de la API de pedidos de Handy para estar guardándolos de manera local en una base de datos</p>

<br>
<h3>Funcionalidad</h3>
<br>
<ul>
  <li>Polling Automatico</li>
  <li>Almacenamiento Local</li>
  <li>Detección de pedidos eliminados</li>
  <li>Logs de actividades</li>
</ul>
<br>
<h3>Requerimientos</h3>
<br>
<ul>
  <li>Instalar en un servidor local</li>
  <li>El intervalo del tiempo de polling es de 10 minutos pero se puede cambiar</li>
  <li>Campos de la base de datos</li>
  <ul>
    <li>Id del usuario</li>
    <li>Id del pedido</li>
     <li>código del producto</li>
     <li>Descripción del cliente</li>
    <li>Monto total de ventas</li>
  </ul>
</ul>

<h3>Instalación</h3>
<br>
<ul>
  <li>Clonar el proyecto</li>
  <li>Configurar la base de datos, si desea cambiar de motor de base datos, por defecto trabaja con H2</li>
  <li>Cambiar las credenciales de autenticación de Handy con los siguientes pasos</li>
  <ul>
    <li>Crear un archivo .env en la raíz del proyecto.</li>
    <li>DDentro del archivo, definir una variable de entorno.</li>
    <li>La variable debe llamarse API_TOKEN.</li>
    <li>Después de API_TOKEN, agregar el signo "="</li>
    <li>A continuación, escribir tu token de usuario de Handy.</li>
  </ul>
  <li>Ejecutar la aplicación</li>
</ul>
