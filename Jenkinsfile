pipeline {
    agent any
    tools{
        maven 'Maven'
        git 'git'
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
                    docker.inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        sh 'docker-compose build'
                        echo 'Docker-compose-build Build Image Completed'
                    }
                }
            }
        }
    }
}
