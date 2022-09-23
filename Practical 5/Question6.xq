for $b in doc("Musicians.xml")/musicians
  return<musician>
  {$b/musician[@type="solo"][2]/name}
  {$b/musician[@type="solo"][2]/genre}
  </musician>