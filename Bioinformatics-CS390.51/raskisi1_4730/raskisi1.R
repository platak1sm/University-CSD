#Manos Platakis 4730 1h Askhsh 390.51

library(stringdist)

getData = function(filepath) {
  con = file(filepath, "r")
  data = list()
  while ( TRUE ) {
    line = readLines(con, n = 1)
    if ( length(line) == 0 ) {
      break
    }
    if(length(grep(">", line)) > 0){
      name = gsub("^>(\\w+)", replacement="\\1", x=line)
      data[[name]] = ""
    }
    else{
      #line = gsub("\r?\n|\r", "", line)
      ##print(line)
      data[[name]] = paste(data[[name]], line, sep="")
    }
  }
  close(con)
  return(data)
}


processSequences = function(seqList, len=5){
  x = sapply(seqList, function(i){
    v = strsplit(i,"")[[1]]
    sapply(1:(length(v)-len+1), function(j){paste(v[j:(j+len-1)],
                                                  collapse="")})
  })
  table(x)
}


getProb = function(foreground, background){
  probs = vector("numeric", length=length(foreground))
  sumforground = sum(foreground)
  sumbackground = sum(background)
  for(i in 1:length(foreground)){
    bcounts = 0
    if( names(foreground)[i] %in% names(background)){
      bcounts = background[[names(foreground)[i]]]
      prob = phyper(q=foreground[i]-1, m = bcounts, n = sumbackground -
                      bcounts, k = sumforground, lower.tail = FALSE)
      probs[i] = prob
    }else{
      bcounts = 1
      prob = phyper(q=foreground[i]-1, m = bcounts, n = sumbackground -
                      bcounts, k = sumforground, lower.tail = FALSE)
      probs[i] = prob
    }
  }
  names(probs) = names(foreground)
  return(sort(probs, decreasing=FALSE))
}


getSmallPvalues = function(pvalues){
  s = 1
  probs = vector()
  for(x in 1:length(pvalues)){
    if(pvalues[x]<0.001){
      probs[s] = pvalues[x]
      names(probs)[s] = names(pvalues)[x]
      s = s+1
    }
  }
  return(probs)
}


getAllInstances = function(candidate, foreground, threshold){
  allnames = names(foreground)
  motifstrings = c()
  for(i in 1:length(allnames)){
    if( stringdist(candidate, allnames[i], method = "hamming") <
        threshold){
      motifstrings = c(motifstrings, rep(allnames[i], foreground[i]))
    }
  }
  return(motifstrings)
}


getPWM = function(stringMotifs, length=6, alphabet =c("A", "C", "G", "T"),
                  freqs = rep(0.25, 4)){
  pfm = matrix(0, nrow=4, ncol=length)
  row.names(pfm) = alphabet
  for(i in 1:length(stringMotifs)){
    v = unlist(strsplit(stringMotifs[i], "")) ## or
    strsplit(stringMotifs[i], "")[[1]]
    for(j in 1:length(v)){
      pfm[v[j], j] = pfm[v[j], j] + 1
    }
  }
  ppm = pfm/colSums(pfm)
  pwm = pwm = log2((ppm+1e-4)/freqs)
  return(list(pwm=pwm, ppm=ppm))
}


getScoreSimple = function(vstring, pwm){
  score = 0
  v = strsplit(vstring, "")[[1]]
  scores = vector("numeric", length=length(v)-ncol(pwm)+1)
  for(i in 1:(length(v)-ncol(pwm)+1)){
    score = 0
    for(j in 1:ncol(pwm)){
      letter = v[i+j-1]
      score = score + pwm[letter, j]
    }
    scores[i] = score
  }
  return(scores)
}

# Set working directory
wdir <- getwd()

# Load data for glycolysis, gluconeogenesis and random data pathways
glycolysis_path <- file.path(wdir, "glycolysis.txt")
glycolysis_data <- getData(glycolysis_path)

gluconeogenesis_path <- file.path(wdir, "gluconeogenesis.txt")
gluconeogenesis_data <- getData(gluconeogenesis_path)

random_path <- file.path(wdir, "randomseqs.txt")
random_data <- getData(random_path)

# Process sequences
motiflen <- 8
foreground1 <- processSequences(glycolysis_data, motiflen)
foreground2 <- processSequences(gluconeogenesis_data, motiflen)
background <- processSequences(random_data, motiflen)

# Find the p-values of the 2 different pathways
pvalues1 <- getProb(foreground1, background)
pvalues2 <- getProb(foreground2, background)

# Find the p-values < 0.001 of each vector
overrepglycolysis <- getSmallPvalues(pvalues1)
overrepgluconeogenesis <- getSmallPvalues(pvalues2)
#print(length(overrepglycolysis))
#print(length(overrepgluconeogenesis))

# Find and print the common overrepresented substrings
allnames <- c(names(overrepglycolysis), names(overrepgluconeogenesis))
cnames <- allnames[duplicated(allnames)]
print(cnames)
print(length(cnames))

# Find and print the motif Strings for each pathway
motifstr1 <- getAllInstances(names(overrepglycolysis)[1], foreground1, 3)
print(motifstr1)
motifstr2 <- getAllInstances(names(overrepgluconeogenesis)[1], foreground2, 3)
print(motifstr2)

# Build the PWM for each pathway
ps1 <- getPWM(motifstr1, length = motiflen, alphabet = c("A", "C", "G", "T"), freqs = rep(0.25, 4))
pwm1 <- ps1[[1]]

ps2 <- getPWM(motifstr2, length = motiflen, alphabet = c("A", "C", "G", "T"), freqs = rep(0.25, 4))
pwm2 <- ps2[[1]]

# Get scores for each pathway and print them 
res1 <- t(sapply(glycolysis_data, getScoreSimple, pwm1))
res2 <- t(sapply(gluconeogenesis_data, getScoreSimple, pwm2))
cat("Glycolysis scores:\n")
print(res1)
cat("Gluconeogenesis scores:\n")
print(res2)

