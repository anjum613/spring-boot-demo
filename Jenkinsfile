pipeline {
    agent any

    environment {
        scannerHome = tool 'SonarQubeScanner'  // the name of the SonarQube Scanner you set in Jenkins global tool config
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
                    bat "${scannerHome}/bin/sonar-scanner.bat"
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
