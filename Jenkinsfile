pipeline {
    agent any
    tools{
        maven 'Maven'
        git 'git'   
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
    sh 'docker-compose build'     
    echo 'Docker-compose-build Build Image Completed'                
    }           
}
    }
}
