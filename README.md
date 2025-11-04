# Analizador Sintáctico JSON LL(1)

Este proyecto implementa un **analizador sintáctico descendente recursivo (LL(1))** para archivos JSON.  
El programa verifica si la estructura del archivo es sintácticamente válida según una gramática simplificada del formato JSON.

---

## Tecnologías Utilizadas

- **Lenguaje:** Java  
- **IDE:** NetBeans (utilizado para el desarrollo)  
- **Herramientas:** Terminal

---

## Requerimientos

- **Java JDK 17** o superior  
- **Terminal (CMD o Bash)** para la ejecución  
- Archivos de entrada con formato **JSON válido o de prueba**

---

## Archivos Principales

El proyecto está compuesto por los siguientes archivos:

- `Main.java` → Punto de entrada del programa  
- `Lexer.java` → Analizador léxico: genera tokens a partir del archivo JSON  
- `Parser.java` → Analizador sintáctico: valida la estructura del JSON  
- `Token.java` → Clase que representa los tokens  
- `TokenType.java` → Enumeración de tipos de token reconocidos  
- `fuente.txt` → Archivo de ejemplo a analizar

---

## Instrucciones de Uso

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Naidess/TP02_compiladores.git
```

### 2. Mover a la carpeta 

```bash
cd .\AnalizadorSintactico\
```

### 3. Compilar el Proyecto

```bash
javac -d . src/analizadorsintactico/*.java
```

### 4. Ejecutar el Analizador

```bash
java -cp src analizadorsintactico.AnalizadorSintactico src/fuente.txt
```
- `fuente.txt` → nombre del archivo JSON de ejemplo a analizar.

---
## Funcionamiento Interno

1. `Lexer.java` analiza el archivo y genera una lista de tokens.
2. `Parser.java` aplica la gramática JSON simplificada para validar la estructura.
3. Si se detectan errores, el analizador utiliza métodos de sincronización (Panic Mode) para continuar con el análisis sin detenerse abruptamente.
4. Finalmente, muestra si el archivo es válido o detalla los errores encontrados.

---
## Gramática Simplificada
```bash
json → element EOF
element → object | array
object → { attributes-list } | {}
attributes-list → attribute ( , attribute )*
attribute → string : attribute-value
attribute-value → element | string | number | true | false | null
array → [ element-list ] | []
element-list → element ( , element )*
```