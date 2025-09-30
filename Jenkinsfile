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

        stage('Test') {
            steps {
                // Run tests with JaCoCo agent to generate coverage data
                bat 'mvn test -Djacoco.dataFile=target/jacoco.exec'
            }
            post {
                always {
                    // Publish test results
                    junit 'target/surefire-reports/*.xml'
                    // (Removed deprecated jacoco step)
                }
            }
        }

        stage('SonarCloud Analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
                    bat "\"${scannerHome}\\bin\\sonar-scanner.bat\" " +
                        "-Dsonar.projectKey=anjum613_spring-boot-demo " +
                        "-Dsonar.organization=anjum613 " +
                        "-Dsonar.java.binaries=target/classes " +
                        "-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }
    }
}
