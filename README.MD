# Trabajo Práctico Kotlin Multiplatform Mobile
## Objetivo
El objetivo de este trabajo práctico es que los alumnos puedan aplicar todos los
conocimientos adquiridos durante la cursada de la materia y más, ya que se irán
encontrando con desafíos técnicos nuevos a medida que avancen en la confección de
una aplicación completa de inicio a fin.
## Temática
Los alumnos recibirán el código fuente de una aplicación Android completamente
funcional y deberán migrarla progresivamente a un proyecto Kotlin multiplataforma.
Para ello deberán:
-  Descargar el código fuente de la aplicación y ejecutarla en Android Studio. La URL
del repositorio es: https://github.com/brianbayarri/pokedex
- Organizar el proyecto siguiendo la arquitectura data-domain-ui
- Crear un proyecto KMM y copiar las clases y dependencias de la aplicación de
Android al módulo nativo Android, para poder compilar y ejecutar la aplicación.
- Separar las clases en las distintas capas de arquitectura. Compilar y ejecutar la
aplicación.
- Migrar el cliente API de Retrofit a Ktor. Compilar y ejecutar la aplicación.
- Migrar la serialización de Gson a Kotlin Serialization. Compilar y ejecutar la
aplicación.
- Mover las clases del cliente API (capa de datos) al módulo compartido. Compilar y
ejecutar la aplicación.
- Implementar en una base de datos local el comportamiento de una memoria caché
ante fallos en la comunicación con la API utilizando SQLDelight