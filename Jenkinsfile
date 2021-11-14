pipeline {
	agent any
	environment { 
        registry = "youssefmarzouk/devops-timesheet" 
        registryCredential = 'dockerhub'
        dockerImage = '' 
    }
	stages{
			stage('Clean Package'){
				steps{
					bat "mvn clean package"
				}				
			}

			
            stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }
			stage('Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
			
		} 
}