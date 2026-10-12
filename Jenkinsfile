pipeline {
    agent any

    tools {
        maven 'Maven3' 
    }

    environment {
        DOCKER_BIN = '"C:\\Users\\tuhi8\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe"'
        IMAGE_NAME = 'multibranch-demo-app'
    }

    stages {
        stage('Checkout & Detect Branch') {
            steps {
                echo "==========================================="
                echo "Detected Active Branch: ${env.BRANCH_NAME}"
                echo "==========================================="
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo "Compiling code on branch: ${env.BRANCH_NAME}"
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running unit tests for: ${env.BRANCH_NAME}"
                bat 'mvn test'
            }
        }

        stage('Package & Containerize') {
            when {
                branch 'main'
            }
            steps {
                echo "Branch is main -> Proceeding to package and build image..."
                bat 'mvn package -DskipTests'
            }
        }

        stage('Feature Validation') {
            when {
                not { branch 'main' }
            }
            steps {
                echo "Feature branch detected (${env.BRANCH_NAME}) -> Verified successfully without deployment."
            }
        }
    }

    post {
        always {
            echo "Finished execution for branch: ${env.BRANCH_NAME}"
        }
    }
}