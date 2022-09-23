for $b in doc("Musicians.xml")/musicians/musician
  where $b[@type="solo"]
  return<musician>
  {$b}
  </musician>