pipeline {
    agent any
    tools{
        maven 'Maven'
        git 'git'   
        docker 'docker'
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
    stage('Building Docker Image') {
      steps {
        script {
          sh "docker-compose -f ./docker-compose.yml build"
        }
      }
    }
    }
}
