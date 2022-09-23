for $b in doc("Musicians.xml")/musicians/musician
  return<albums>
  {$b/albums/album[@year>2007]}
  </albums>