pipeline {
    agent any
    tools{
        maven 'Maven'
        git 'git'
        dockerTool 'docker' // Corrected to use dockerTool
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
                    // Assuming 'docker' is the name of the Docker tool configured in Jenkins
                    docker.withTool('docker') {
                        sh 'docker-compose build'
                        echo 'Docker-compose-build Build Image Completed'
                    }
                }
            }
        }
    }
}
