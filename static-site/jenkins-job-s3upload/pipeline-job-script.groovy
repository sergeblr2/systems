pipeline {
    agent any
    environment {
        AWS_DEFAULT_REGION = "us-east-1"
    }
    stages {

        stage('Pull') {
            steps {
                git credentialsId: '4f06a750-9a13-48df-9027-9cb8cf49efaa',
                        url: 'https://sergeblr2@github.com/sergeblr2/systems'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('copyArtifacts') {
            steps {
                bat ''
            }
        }

        stage('staticS3Upload') {
            steps {
                withAWS(region: 'us-east-1', credentials: 'buck-creds') {
                    s3Upload(bucket: 'sergb.webstatic', workingDir: 'static-site/target', includePathPattern: '**/*');
                }
            }
        }

    }
}