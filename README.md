# SecondChanceTech

This guide will help you set up the development environment and deploy the **SecondChanceTech** web application locally using Tomcat.

---

## **Prerequisites**

Before you start, make sure you have the following installed on your system:

1. **Java JDK 25**
    - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html)
    - Make sure `JAVA_HOME` is set and `java` is available in your PATH.

2. **IntelliJ IDEA Community Edition**
    - Download from [IntelliJ IDEA](https://download-cdn.jetbrains.com/idea/idea-2025.3.exe)

3. **Apache Tomcat 10.1.50**
    - Download from [Tomcat Downloads](https://tomcat.apache.org/download-10.cgi)
    - During installation, **set up credentials**:
        - **Username:** `admin`
        - **Password:** `admin`

---

## **IntelliJ Setup**

1. Open IntelliJ IDEA.
2. Install the **Smart Tomcat** plugin:
    - Go to `File` → `Settings` → `Plugins` → `Marketplace` → Search `Smart Tomcat` → Install.
    - Restart IntelliJ after installation.

3. Configure Tomcat in IntelliJ:
    - Go to `Run` → `Edit Configurations`.
    - Click `+` → Select `Tomcat Server` → `Local`.
    - Set the **Tomcat Home Directory** to the folder where you installed Tomcat 10.1.50.
    - Set username/password as `admin` / `admin`.

4. Deploy the project:
    - In the Tomcat configuration, click `Deployment` → `+` → `Artifact` → Select `SecondChanceTech:war exploded`.
    - Apply the changes.

---

## **Running the Application**

1. Start the Tomcat server from IntelliJ.
2. Access the application in your browser:  
