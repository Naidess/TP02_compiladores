# Analizador Sintáctico JSON LL(1)

Este proyecto implementa un **analizador sintáctico descendente recursivo (LL(1))** para archivos JSON.  
Es una **evolución del analizador léxico** desarrollado en el [TP01](https://github.com/Naidess/TP01_compiladores): el programa primero muestra la salida del análisis léxico (tokens reconocidos con indentación jerárquica) y luego verifica si la estructura del archivo es sintácticamente válida según una gramática simplificada del formato JSON.

---

## Responsables
- Raul Fernando Servian Cespedes 
- Alejandro Daniel Vera Escobar 

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

- `AnalizadorSintactico.java` → Punto de entrada del programa  
- `Lexer.java` → Analizador léxico: genera tokens a partir del archivo JSON e imprime la salida léxica con formato jerárquico  
- `Parser.java` → Analizador sintáctico: valida la estructura del JSON  
- `Token.java` → Clase que representa los tokens  
- `TokenType.java` → Enumeración de tipos de token reconocidos  
- `src/fuente.txt` → Archivo de ejemplo a analizar

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
javac -d build src/analizadorsintactico/*.java
```

### 4. Ejecutar el Analizador

```bash
java -cp build analizadorsintactico.AnalizadorSintactico src/fuente.txt
```
- `src/fuente.txt` → ruta del archivo JSON de ejemplo a analizar (puede reemplazarse por cualquier otro archivo).

---
## Funcionamiento Interno

El programa ejecuta dos fases sobre el archivo de entrada:

### Fase 1: Análisis Léxico
1. `Lexer.java` recorre el archivo y genera una lista de tokens (`STRING`, `NUMBER`, `PR_TRUE`, `PR_FALSE`, `PR_NULL`, `L_LLAVE`, `R_LLAVE`, `L_CORCHETE`, `R_CORCHETE`, `DOS_PUNTOS`, `COMA`).
2. Soporta **números negativos y decimales** (ej. `-12.5`).
3. Imprime la salida léxica con **indentación jerárquica** según el nivel de anidamiento, mostrando los **arrays vacíos en línea** (`L_CORCHETE R_CORCHETE`).
4. Los errores léxicos (caracteres o palabras no reconocidas) se reportan indicando el **número de línea**.

### Fase 2: Análisis Sintáctico
1. `Parser.java` aplica la gramática JSON simplificada para validar la estructura.
2. Si se detectan errores, el analizador utiliza métodos de sincronización (Panic Mode) para continuar con el análisis sin detenerse abruptamente.
3. Finalmente, muestra si el archivo es válido o detalla los errores encontrados.

---
## Salida de Ejemplo

```
=== Análisis Léxico ===
L_LLAVE
	STRING DOS_PUNTOS L_CORCHETE
		L_LLAVE
			STRING DOS_PUNTOS NUMBER COMA 
			STRING DOS_PUNTOS STRING COMA 
			STRING DOS_PUNTOS PR_FALSE COMA 
			STRING DOS_PUNTOS L_CORCHETE R_CORCHETE
		R_LLAVE
	...
R_LLAVE

=== Análisis Sintáctico ===
El archivo es sintácticamente válido.
```

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
