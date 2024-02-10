pipeline {

	agent {
  		label "master"
 	}
 
	parameters {


  		choice(name: 'tags', choices: ['@Smoke', '@Regression', '@Smoke or @Regression'], description: 'Please pass cucumber tag to run')
    	choice(name: 'env', choices: ['qa','int2'], description: 'Please select environment to execute tests')
    	string(name:'MailingList', defaultValue: 'skanungo2007@gmail.com',description: 'Email mailing list', trim: true)
 	}

	options {
	    
	    
	    	disableConcurrentBuilds()
	    	disableResume()
	    	
	}
	
	environment {
	    
	    DEFAULT_MAIL_LIST = 'skanungo2007@gmail.com,skanungo@live.in,ratnak.2015@gmail.com'
	}



  	stages {
    	stage('Automation') {
        	steps {

            	script {

                	bat "mvn clean install -Dtags=\"${tags}\" -Denv=\"${ENV}\""


                       }


                 }
            }
    }

	post {
    
    	always {

        	cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '*.json', jsonReportDirectory: 'target/cucumber-reports/', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1

     	}
     	
     	failure {
     	    
     	    mail to: "${params.MailingList},${env.DEFAULT_MAIL_LIST}", subject: "Job ${env.JOB_NAME} failed", body: "Check the URL below for more information\n\n${env.BUILD_URL}cucumber-html-reports/overview-features.html"
     	
     	}
     	
     	success {
     	    
     		mail to: "${params.MailingList},${env.DEFAULT_MAIL_LIST}", subject: "Job ${env.JOB_NAME} passed", body: "Check the URL below for more information\n\n${env.BUILD_URL}cucumber-html-reports/overview-features.html"
     	    
     	}


   	}
   	
 }

