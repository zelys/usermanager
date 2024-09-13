# Administrador de Usuarios Java

## Introducción
Este proyecto es un sistema de administración de usuarios desarrollado en Java 17, que utiliza Swing para la interfaz gráfica y EclipseLink como proveedor de persistencia con MySQL como base de datos. El sistema incluye autenticación de usuarios, gestión de roles y funcionalidades CRUD para la administración de usuarios.

## Tecnologías Utilizadas
- Java 17
- Swing (para la interfaz gráfica)
- EclipseLink (proveedor de persistencia JPA)
- MySQL (base de datos)
- Maven (gestor de dependencias)

## Dependencias Principales
- EclipseLink
- MySQL Connector/J

## Funcionalidades Principales
1. **Autenticación de Usuarios**: Ventana de login para acceso al sistema.
2. **Gestión de Roles**: Implementación de permisos basados en roles de usuario.
3. **Administración de Usuarios**:
    - Creación de nuevos usuarios
    - Edición de usuarios existentes
    - Eliminación de usuarios
    - Listado de usuarios
4. **Validaciones**:
    - Prevención de nombres de usuario duplicados
    - Validación de campos requeridos
    - Validación de formato de nombre de usuario y contraseña (sin espacios en blanco)

## Guía de Instalación y Ejecución

### Prerrequisitos
- Java JDK 17 o superior
- Maven
- MySQL Server

### Pasos para la Ejecución
1. Clonar el repositorio:
   ```
   git clone https://github.com/tu-usuario/usermanager.git
   ```

2. Navegar al directorio del proyecto:
   ```
   cd usermanager
   ```

3. Compilar el proyecto con Maven:
   ```
   mvn clean install
   ```

4. Configurar la base de datos:
    - Crear una base de datos MySQL
    - Actualizar el archivo de configuración de persistencia con los detalles de conexión a la base de datos

5. Ejecutar la aplicación:
   ```
   java -jar target/usermanager.jar
   ```

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir los cambios propuestos antes de realizar un pull request.

## Licencia
Apache License 2.0