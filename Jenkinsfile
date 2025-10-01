pipeline {
    agent any

    environment {
        // SonarScanner tool installation name in Jenkins
        scannerHome = tool 'SonarQubeScanner'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test & Coverage') {
            steps {
                bat 'mvn verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarCloud Analysis') {
            environment {
                // Inject the SonarCloud token stored as global credential ID sonarcloud-token
                SONAR_TOKEN = credentials('sonarcloud-token')
            }
            steps {
                withSonarQubeEnv('SonarCloud') {
                    // Use the SonarScanner bundled with Jenkins, passing authentication and coverage XML
                    bat "\"${scannerHome}\\bin\\sonar-scanner.bat\" " +
                        "-Dsonar.projectKey=anjum613_spring-boot-demo " +
                        "-Dsonar.organization=anjum613 " +
                        "-Dsonar.host.url=https://sonarcloud.io " +
                        "-Dsonar.login=%SONAR_TOKEN% " +
                        "-Dsonar.sources=src/main/java " +
                        "-Dsonar.tests=src/test/java " +
                        "-Dsonar.java.binaries=target/classes " +
                        "-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }
    }
}
