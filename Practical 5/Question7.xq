for $b in doc("Musicians.xml")/musicians/musician
  where $b/name = "Ben Folds"
  return<musician>
  {$b/albums/album[4]}
  </musician>