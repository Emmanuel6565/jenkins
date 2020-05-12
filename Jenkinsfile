String cront_string = "H(0-29)/15"

properties ([
    pipelineTriggers([pollSCM(cront_string)])
])

pipeline {
    agent any
   
    stages {
        
        stage('Compile Project') {
            steps {
                checkout scm
                echo "-=- compiling project -=-"
                sh "./mvnw clean compile"
            }
        }
        
        stage('Test') {
            parallel {
                stage('Unit tests') {
                    steps {
                        echo "-=- execute unit tests -=-"
                        sh "./mvnw test"
                        junit 'target/surefire-reports/*.xml'
                        step( [ $class: 'JacocoPublisher' ] )
                    }
                    post {
                        succes {
                            
                        }
                        failure {
                            
                        }
                    }
                }
                stage('Mutation tests') {
                    steps {
                        echo "-=- execute mutation tests -=-"
                        sh "./mvnw org.pitest:pitest-maven:mutationCoverage"
                    }
                    post {
                        succes {
                            //send notification
                        }
                        failure {
                           //send notification
                        }
                    }

                }
            }
        }

        
        stage('Package') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                echo "-=- packaging project -=-"
                sh "./mvnw package -DskipTests"
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
       
        stage('Build && push Docker Image to Nexus') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                sh "./mvnw docker:build -DpushImage -DpushImageTag=latest -Dmaven.test.skip=true"
            }
        }
        
        stage('Deploy for development') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                //ansible job test
                echo "-=- Ansible job for test env"
                ansibleTower async: false, credential: '', extraVars: '', importTowerLogs: false, importWorkflowChildLogs: false, inventory: '', jobTags: '', jobTemplate: 'Production', jobType: 'run', limit: '', removeColor: false, skipJobTags: '', templateType: 'workflow', throwExceptionWhenFail: false, towerCredentialsId: 'ansible', towerServer: 'ansible', verbose: false

            }
        }
    
}

    
    

/*
 stage('Git Clone') {
     steps {
         git branch: 'env.BRANCH_NAME', credentialsId: 'f6fb2c09-43ba-4a49-b535-4a8cfd2e3757', url: 'https://github.com/Emmanuel6565/jenkins.git'
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
*/
