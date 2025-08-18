pipeline {
    agent any

    tools {
        maven 'Maven3'  // make sure Maven is configured in Jenkins Global Tool Configuration
        jdk 'JDK17'     // use your configured JDK version
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running TestNG tests...'
                sh 'mvn test'
            }
            post {
                always {
                    echo 'Publishing TestNG results...'
                    junit '**/target/surefire-reports/testng-results.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add deployment steps here
            }
        }
    }
}
