pipeline {
    agent any
    tools{
        maven 'maven 3.9.6'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/jenkins']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/LhassanTkarkib/Connexia']]])
                sh 'mvn clean install'
            }
        }
   }
}
