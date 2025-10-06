pipeline {
    agent any
    environment {
        scannerHome = tool 'SonarQubeScanner'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test & Coverage') {
            steps {
                bat 'mvn test jacoco:report'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
                }
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
        stage('SonarCloud Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonarcloud-token')
            }
            steps {
                withSonarQubeEnv('SonarCloud') {
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
    post {
        always {
            echo 'Pipeline complete. Review test results and coverage artifacts.'
        }
    }
}
