for $b in doc("Musicians.xml")/musicians/musician
  where $b/albums/album = "Lateralus"
  return<musician>
  {$b/name}
  </musician>