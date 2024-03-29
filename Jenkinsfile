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
            agent {
                docker {
                    image 'docker:24.0.5'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                sh 'docker-compose build'
                echo 'Docker-compose-build Build Image Completed'
            }
        }
    }
}
