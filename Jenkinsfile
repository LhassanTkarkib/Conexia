pipeline {
    agent any
    tools{
        maven 'Maven'
        git 'git'
        // Ensure you have a Docker installation configured in Jenkins with the name 'docker'
    }
    environment {
        PATH = "${env.PATH}:/usr/local/bin"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'dockertest', url: 'https://github.com/LhassanTkarkib/Connexia'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps{
                script {
                    // Use the Docker tool to execute docker-compose commands
                    docker.withTool('docker') {
                        sh 'docker-compose build'
                        echo 'Docker-compose-build Build Image Completed'
                    }
                }
            }
        }
    }
}
