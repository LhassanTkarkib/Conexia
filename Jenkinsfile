pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
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
