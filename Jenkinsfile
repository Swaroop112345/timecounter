pipeline{

    agent any

    environment{
        BROWSER='Chrome'
        TAG='@Assignment'
    }

    stages {

        stage ('Complile Stage'){

            steps(){
                withMaven(maven: maven_3_8_2){
                    sh 'mvn clean install -DskipTests' 
                }
            }
        }

        stage ('Test Stage'){

            steps(){
                withMaven(maven: maven_3_8_2){
                    echo "Browser : $BROWSER  Tag : $TAG"
                    sh 'mvn -Dbrowser="$BROWSER" -Dcucumber.filter.tags="'$TAG'" clean verify' 
                }
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
                body: readFile'"target/sure-fire-reports/emailablereport.html', 
                subject: "Email from '$PROJECT_DEFAULT_SUBJECT'", 
                to: 'swaroop0142@gmail.com');
            }
        }

    }
    
}
