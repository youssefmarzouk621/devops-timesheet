pipeline {
	agent any
	stages{
			stage('Clean and Install'){
				steps{
					bat "mvn clean"
					bat "mvn install"
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