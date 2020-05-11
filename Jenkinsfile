// send notification via mail or slack

/*def notity(String StageName, String Status){
    emailext(
        subject: "STARTED: Job '${env.BRANCH_NAME} ${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
    body: """<p>STATUS: Status Job '{env.BRANCH_NAME} ${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p> <p>check log at <a href=${env.BUILD_URL}>${env.JOB_NAME}</a></p>"""
    recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          
    )
}*/

// set env variables

pipeline {
   agent any
   
    stages {
        /*stage('Git Checkout') {
            steps {
                checkout scm
            }
        }*/
        /*
        stage('Git Clone') {
            steps {
                git branch: 'env.BRANCH_NAME', credentialsId: 'f6fb2c09-43ba-4a49-b535-4a8cfd2e3757', url: 'https://github.com/Emmanuel6565/jenkins.git'
            }
        }*/
        
        // stage for feature branch (Tests)
        stage('Compile') {
            steps {
                echo "-=- compiling project -=-"
                sh "./mvnw clean compile"
            }
        }
        stage('Unit tests') {
            steps {
                echo "-=- execute unit tests -=-"
                sh "./mvnw test"
                junit 'target/surefire-reports/*.xml'
                step( [ $class: 'JacocoPublisher' ] )
            }
        }
        
        stage('Mutation tests') {
            steps {
                echo "-=- execute mutation tests -=-"
                sh "./mvnw org.pitest:pitest-maven:mutationCoverage"
            }
        }
        
        stage('Package') {
            steps {
                echo "-=- packaging project -=-"
                sh "./mvnw package -DskipTests"
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        
        
        stage('Code inspection & quality gate') {
            steps {
                echo "-=- run code inspection & check quality gate -=-"
                withSonarQubeEnv('Sonarqube') {
                    sh "./mvnw sonar:sonar"
                }
                timeout(time: 10, unit: 'MINUTES') {
                    //waitForQualityGate abortPipeline: true
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        
       
        stage('Build && push Docker Image to Nexus') {
            steps {
                sh "./mvnw docker:build -DpushImage -DpushImageTag=latest -Dmaven.test.skip=true"
            }
        }
        
        stage('Deploy for development') {
            steps {
                //ansible job test
                echo "-=- Ansible job for test env"
                ansibleTower async: false, credential: '', extraVars: '', importTowerLogs: false, importWorkflowChildLogs: false, inventory: '', jobTags: '', jobTemplate: 'Production', jobType: 'run', limit: '', removeColor: false, skipJobTags: '', templateType: 'workflow', throwExceptionWhenFail: false, towerCredentialsId: 'ansible', towerServer: 'ansible', verbose: false
        
            }
        }
        
        // Integration tests
        /*
        stage('Integration tests') {
            steps {
                echo "-=- execute integration tests -=-"
                sh "curl --retry 5 --retry-connrefused --connect-timeout 5 --max-time 5 http://localhost:8090/"
                sh "./mvnw failsafe:integration-test failsafe:verify -DargLine=\'-Dtest.target.server.url=http://localhost:8090/'"
                sh "java -jar target/dependency/jacococli.jar dump --address app-java --port 6300 --destfile target/jacoco-it.exec"
                junit 'target/failsafe-reports/*.xml'
                jacoco execPattern: 'target/jacoco-it.exec'
            }
        }
        
        stage('Performance tests') {
            steps {
                echo "-=- execute performance tests -=-"
                sh "./mvnw jmeter:jmeter jmeter:results -Djmeter.target.host=app-java -Djmeter.target.port=8090 -Djmeter.target.root=/"
                perfReport sourceDataFiles: 'target/jmeter/results/*.csv'
            }
        }*/
        

        /*
        stage('Deploy for Prod') {
            steps {
                ansibleTower async: false, credential: '', extraVars: '', importTowerLogs: false, importWorkflowChildLogs: false, inventory: '', jobTags: '', jobTemplate: 'Production ', jobType: 'run', limit: '', removeColor: false, skipJobTags: '', templateType: 'workflow', throwExceptionWhenFail: false, towerCredentialsId: 'ansible', towerServer: 'ansible', verbose: false            }
        }*/
    }
    
}
