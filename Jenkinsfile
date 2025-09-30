pipeline {
    agent any

    environment {
        scannerHome = tool 'SonarQubeScanner'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

    stage('SonarCloud Analysis') {
        steps {
            withSonarQubeEnv('SonarCloud') {
                bat "${scannerHome}\\bin\\sonar-scanner.bat " +
                    "-Dsonar.projectKey=anjum613_spring-boot-demo " +
                    "-Dsonar.organization=anjum613 " +
                    "-Dsonar.java.binaries=target/classes"
            }
        }
    }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}