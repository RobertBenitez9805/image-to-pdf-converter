# Image to PDF Converter

## 📌 Descripción
Esta herramienta en **Java Spring Boot** permite convertir una carpeta local de imágenes en un archivo PDF.  
Cada imagen se agrega como una página independiente del PDF, manteniendo el estilo visual original de la imagen.

El algoritmo asegura que:
- Las imágenes se procesan en orden numérico (`1.jpg`, `2.jpg`, ..., `50.jpg`).
- La cantidad de páginas en el PDF corresponde a la cantidad de imágenes en la carpeta.
- El PDF conserva el estilo original de las imágenes (opción de renderizado directo).

---

## ⚙️ Requisitos
- **Java 17** o superior
- **Maven 3.9+**
- **Spring Boot 3.x**
- Dependencias adicionales:
    - `org.apache.pdfbox` → para generar el PDF

---

## 🚀 Uso

### 1. Configuración
Clonar el repositorio y compilar el proyecto:
```bash
git clone https://github.com/RobertBenitez9805/image-to-pdf-converter.git
cd image-to-pdf-converter
mvn clean install
```

### 2. Ejecutar la aplicación
Levantar el servicio Spring Boot:
```bash
mvn spring-boot:run
```

### 3. Endpoint principal
La aplicación expone un endpoint para generar el PDF:

**POST** `/api/pdf/from-folder`

#### Parámetros:
- `folderPath` → ruta local de la carpeta con imágenes
- `outputPdfPath` → ruta donde se guardará el PDF resultante

#### Ejemplo de request (JSON):
```json
{
  "folderPath": "C:/imagenes/input",
  "outputPdfPath": "C:/pdf/output.pdf"
}
```

### 4. Ejemplo de salida
- Si la carpeta tiene `50` imágenes → el PDF tendrá `50` páginas.

---

## 📂 Estructura del Proyecto
```
src/main/java/org/bitsolutions/imagetopdfconverter
│── controller/
│   └── Image2PdfController.java
│── service/
│   └── Image2PdfService.java
│── dto/
│   └── RequestDto.java
│── ImagePdfApplication.java
```

---

## 📝 Roadmap
- [x] Convertir imágenes a PDF en orden numérico
- [x] Mantener estilos originales
- [ ] Añadir OCR para extraer texto
- [ ] Configuración de fuentes personalizadas
- [ ] Exportar PDFs protegidos con contraseña

---

## 📄 Licencia
MIT License © 2025 - Desarrollado por Robert
