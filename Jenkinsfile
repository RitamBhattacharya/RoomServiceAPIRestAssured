pipeline {
    agent any

    tools {
        maven 'Maven3'   // configure in Jenkins global tools
        jdk 'JDK17'      // configure in Jenkins global tools
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/RitamBhattacharya/RoomServiceAPIRestAssured.git'
            }
        }

        stage('Build & Test') {
            steps {
                withMaven(maven: 'Maven3') {
                    bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
                }
            }
        }
    }

    post {
        always {
            // Publish TestNG XML reports (from surefire)
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

            // Publish Extent HTML report
            publishHTML(target: [
                reportDir: 'target/ExtentReports',
                reportFiles: 'RoomServiceApiReport.html',
                reportName: 'Room Service API Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            // Archive reports for download
            archiveArtifacts artifacts: 'target/ExtentReports/**, target/surefire-reports/**',
                             fingerprint: true,
                             allowEmptyArchive: true
        }
    }
}
