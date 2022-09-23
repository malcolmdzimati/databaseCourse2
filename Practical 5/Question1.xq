for $b in doc("Musicians.xml")/musicians/musician
  return<musician>
  {$b/name}
  </musician>