# Club DAMA – Gestor deportivo

Aplicación de escritorio para la gestión integral de un club deportivo, desarrollada en JavaFX con base de datos MariaDB e Hibernate. Permite administrar socios, pistas y reservas a través de una interfaz gráfica intuitiva y moderna. Proyecto creado como parte del módulo de acceso a datos.

## Autores

- [@Daniel Illán](https://github.com/YuhManDani)
- [@David Fernández](https://github.com/DavidFM2004)
- [@Jose David Navarro](https://github.com/Joseda005)
- [@Jorge Corbalán](https://github.com/Jorgecs96)

## Características del proyecto

📊 **Dashboard**

- Vista principal con tablas ordenables que muestran de forma resumida:

- Socios registrados

- Pistas disponibles

- Reservas activas

👤 **Gestión de Socios**

- Registrar nuevos socios con datos como DNI, nombre, teléfono y correo.

- Eliminar socios existentes.

📅 **Sistema de Reservas**

- Crear reservas seleccionando socio, pista disponible, fecha, hora y duración.

- Cancelar reservas existentes.

🛠️ **Tecnologías utilizadas**

- Lenguaje: Java

- Framework UI: JavaFX

- Base de datos: MariaDB

- Conexión: JPA e Hibernate

## Estructura del proyecto

```
src/
├── 📂 entidades/     → POJOs: Socio, Pista, Reserva
├── 📂 servicio/      → Lógica de negocio (ClubDeportivo.java)
├── 📂 vista/         → Capa de presentación JavaFX
    ├── 📂 views/         → Formularios y paneles gráficos (dashboard, socios, pistas…)
        ├── 📄 Launcher.java  → Punto de entrada de la aplicación
        └── 📄 MainApp.java   → Cerebro de la aplicación
```

## Conexión a la base de datos

1️⃣ **Requisitos**

**MySQL** en ejecución

Base de datos con nombre: **club_dama**

2️⃣ **Crear tablas necesarias**

**socios** → id_socio, dni, nombre, apellidos, telefono, email

**pistas** → id_pista, deporte, descripcion, disponible

**reservas** → id_reserva, id_socio, id_pista, fecha, hora_inicio, duracion_min, precio

**Además**, la aplicación utiliza el procedimiento almacenado:
sp_crear_reserva

3️⃣ **Configuración de la conexión**

En **src/servicio/ClubDeportivo.java**, creamos el EntityManagerFactory:

private EntityManagerFactory emf;

## Ejecución de la aplicación

**Clona el repositorio**

git clone https://github.com/Jorgecs96/dam-ads-u3-equipoMolina

Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse…).

**Asegúrate de tener configurados:**

**MariaDB JDBC**

**JavaFX SDK**

**Hibernate ORM**

Ejecuta Launcher.java ubicado en src/vista/ para iniciar la aplicación.
