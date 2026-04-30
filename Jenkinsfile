pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/Rachita7102/twitter-clone.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}