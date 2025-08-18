pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Example: compile code
                // sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running Tests...'
                // Example: run unit tests
                // sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Example: deploy code
                // sh './deploy.sh'
            }
        }
    }
}
