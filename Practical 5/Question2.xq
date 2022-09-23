for $b in doc("Musicians.xml")/musicians/musician
  return<album>
  {$b/name}
  {$b/albums/album}
  </album>