pipeline {
	agent any
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