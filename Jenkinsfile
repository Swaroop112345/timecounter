pipeline{

    agent any

      tools {
        maven 'maven_3_8_2'
    }

    environment{
        BROWSER='Chrome'
        TAG='@Assignment'
    }

    stages {

        stage ('Complile Stage'){

            steps{
                    sh 'mvn -f Maersk/pom.xml clean install -DskipTests' 
                }
            }
        

        stage ('Test Stage'){

            steps{
               
                    echo "Browser : $BROWSER  Tag : $TAG"
                    sh 'mvn -f Maersk/pom.xml clean verify'
                    //sh 'mvn -Dbrowser="$BROWSER" -Dcucumber.filter.tags="'$TAG'" clean verify' 
            }
        }

        stage ('Cucumber Reports'){

            steps(){
                    cucumber buildStatus: "UNSTABLE",
                    reportTitle: 'Cucumber Report',
                    fileIncludePattern: "**/cucumber.json",
                    jsonReportDirectory: 'target'

            }
        }

        stage('Allure Reports') {
                steps {
                        script {
                                   allure([
                                   includeProperties: false,
                                   jdk: '',
                                   properties: [],
                                   reportBuildPolicy: 'ALWAYS',
                                   results: [[path: '**/test-output/allure-results']]
                                         ])
                             }
                    }
        
             }

        stage('Email'){
            steps{
                emailext (
                attachLog: true, 
                attachmentsPattern: '**/test-output/SparkReport/*.html,**/test-output/SparkReport/*.pdf',
                body: "Hi Team . PFA the automation Report ", 
                subject: "Email from '$PROJECT_DEFAULT_SUBJECT' ", 
                to: 'swaroop0142@gmail.com');
            }
        }

    }
    
}
