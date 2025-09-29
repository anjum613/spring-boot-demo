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
                        "-Dsonar.projectKey=your_project_key " +
                        "-Dsonar.organization=your_organization_key"
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
