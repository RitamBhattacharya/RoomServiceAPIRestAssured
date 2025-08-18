pipeline {
  agent any

  tools {
    jdk   'JDK17'
    maven 'Maven3'
  }

  stages {
    stage('Checkout') {
      steps {
        git url: 'https://github.com/RitamBhattacharya/RoomServiceAPIRestAssured', branch: 'main'
      }
    }

    stage('Build & Test') {
      steps {
        withMaven(maven: 'Maven3') {
          bat 'mvn -B clean test -Dsurefire.suiteXmlFiles=testng.xml'
        }
      }
    }
  }

  post {
    always {
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

      testNG(
        reportFilenamePattern: 'target/surefire-reports/testng-results.xml',
        showFailedBuilds: true,
        escapeTestDescriptions: true,
        escapeExceptionMessages: true
      )

      publishHTML(target: [
        reportDir: 'target/ExtentReports',
        reportFiles: 'RoomServiceApiReport.html',
        reportName: 'Room Service API Report',
        keepAll: true,
        alwaysLinkToLastBuild: true,
        allowMissing: true
      ])

      archiveArtifacts artifacts: 'target/ExtentReports/**, target/surefire-reports/**',
                       fingerprint: true,
                       allowEmptyArchive: true
    }
  }
}
